package com.adaptativelearning.examstudent;

import com.adaptativelearning.answer.Answer;
import com.adaptativelearning.answer.AnswerService;
import com.adaptativelearning.base.BaseService;
import com.adaptativelearning.exam.Exam;
import com.adaptativelearning.exam.ExamService;
import com.adaptativelearning.parameter.Parameter;
import com.adaptativelearning.parameter.ParameterService;
import com.adaptativelearning.question.Question;
import com.adaptativelearning.question.QuestionService;
import com.adaptativelearning.reinforcement.Reinforcement;
import com.adaptativelearning.reinforcement.ReinforcementService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamStudentService extends BaseService<ExamStudent, Integer>
{
    private static final String ASSIGNED_STATE = "assigned";
    private static final String IN_PROGRESS_STATE = "in_progress";
    private static final String FINISHED_STATE = "finished";
    private static final Integer LOW_DIFFICULTY = 1;
    private static final Integer MEDIUM_DIFFICULTY = 2;
    private static final Integer HARD_DIFFICULTY = 3;
    private static final String NUMBER_QUESTIONS_KEY = "NUMBER_QUESTIONS";
    private static final Integer DEFAULT_NUMBER_QUESTIONS = 15;

    @Autowired
    private ExamService examService;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ReinforcementService reinforcementService;

    @Autowired
    private ExamStudentRepository examStudentRepository;

    public List<ExamStudent> findByStudent(Integer idStudent)
    {
        return examStudentRepository.findByIdStudent(idStudent);
    }

    public void assignStudentsExam(ExamStudentAssignDTO examStudentAssignDTO)
    throws Exception
    {
        Parameter numQuestParam = parameterService.findByParameterKey(NUMBER_QUESTIONS_KEY);

        Integer numberQuestions = numQuestParam != null
            ? Integer.valueOf(numQuestParam.getValue())
            : DEFAULT_NUMBER_QUESTIONS;

        int questionPerDifficulty = numberQuestions / 3;

        Exam exam = examService.findById(examStudentAssignDTO.getExam());

        if (exam != null)
        {
            List<Question> questions = questionService.getAll().stream().filter(question -> question
                .getIdTheme().equals(exam.getIdTheme())).collect(Collectors.toList());

            List<Question> easyQuestions = questions.stream().filter(question -> question
                .getIdDifficulty().equals(LOW_DIFFICULTY)).collect(Collectors.toList());

            List<Question> halfQuestions = questions.stream().filter(question -> question
                .getIdDifficulty().equals(MEDIUM_DIFFICULTY)).collect(Collectors.toList());

            List<Question> hardQuestions = questions.stream().filter(question -> question
                .getIdDifficulty().equals(HARD_DIFFICULTY)).collect(Collectors.toList());

            if (easyQuestions.size() >= questionPerDifficulty
                && halfQuestions.size() >= questionPerDifficulty
                && hardQuestions.size() >= questionPerDifficulty)
            {
                examStudentAssignDTO.getStudents().forEach(student -> {

                    List<ExamStudent> examStudentList = examStudentRepository.searchAssignment(exam
                        .getId(), student);

                    ExamStudent examStudent = new ExamStudent();
                    examStudent.setIdExam(exam.getId());
                    examStudent.setIdStudent(student);
                    examStudent.setQuestions(getQuestions(easyQuestions,
                        halfQuestions,
                        hardQuestions,
                        questionPerDifficulty));
                    examStudent.setTryNumber(examStudentList.size() + 1);
                    examStudent.setState(ASSIGNED_STATE);

                    save(examStudent);
                });
            }
            else
            {
                throw new Exception(
                    "No existen suficientes preguntas almacenadas para construir la evaluación");
            }

        }
        else
        {
            throw new Exception("La evaluación que intenta asignar no existe");
        }
    }

    private String getQuestions(List<Question> easyQuestions,
        List<Question> halfQuestions,
        List<Question> hardQuestions,
        Integer questionPerDifficulty)
    {
        Collections.shuffle(easyQuestions);
        Collections.shuffle(halfQuestions);
        Collections.shuffle(hardQuestions);

        List<Integer> questions = easyQuestions.subList(0, questionPerDifficulty).stream().map(
            Question::getId).collect(Collectors.toList());

        questions.addAll(halfQuestions.subList(0, questionPerDifficulty).stream()
            .map(Question::getId).collect(Collectors.toList()));

        questions.addAll(hardQuestions.subList(0, questionPerDifficulty).stream()
            .map(Question::getId).collect(Collectors.toList()));

        Collections.shuffle(questions);

        return questions.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public ExamStudentTakeDTO takeStudentExam(Integer examStudentId)
    {
        ExamStudent examStudent = findById(examStudentId);
        examStudent.setState(IN_PROGRESS_STATE);
        examStudent = save(examStudent);

        ExamStudentTakeDTO examStudentTakeDTO = new ExamStudentTakeDTO();
        examStudentTakeDTO.setId(examStudent.getId());
        examStudentTakeDTO.setIdExam(examStudent.getIdExam());
        examStudentTakeDTO.setIdStudent(examStudent.getIdStudent());
        examStudentTakeDTO.setArea(examStudent.getExam().getArea());
        examStudentTakeDTO.setState(examStudent.getState());
        examStudentTakeDTO.setTryNumber(examStudent.getTryNumber());

        List<Integer> questionsIds = Arrays.stream(examStudent.getQuestions().split("\\s*,\\s*"))
            .map(Integer::parseInt).collect(Collectors.toList());

        List<Question> questions = new ArrayList<>();

        questionsIds.forEach(questionId -> {
            Question question = questionService.findById(questionId);
            questions.add(question);
        });

        examStudentTakeDTO.setQuestions(questions);

        return examStudentTakeDTO;
    }

    public ExamStudent qualifyExam(ExamStudentQualifyDTO examStudentQualifyDTO)
    {
        ExamStudent examStudent = findById(examStudentQualifyDTO.getId());
        examStudent.setAnswers(examStudentQualifyDTO.getAnswers().stream().map(String::valueOf)
            .collect(Collectors.joining(",")));
        examStudent.setState(FINISHED_STATE);
        examStudent.setRealizationDate(new Date());

        int totalPoints = 0;

        for (Integer idAnswer : examStudentQualifyDTO.getAnswers())
        {
            Answer answer = answerService.findById(idAnswer);

            if (answer.getIsCorrect() != 0)
            {
                totalPoints += getPointsForAnswer(answer);
            }
        }

        List<Integer> questionsIds = Arrays.stream(examStudent.getQuestions().split("\\s*,\\s*"))
            .map(Integer::parseInt).collect(Collectors.toList());

        int totalQuestions = questionsIds.size();
        int questionsPerDifficulty = totalQuestions / 3;
        int possibleTotalPoints =
            (questionsPerDifficulty * 3) + (questionsPerDifficulty * 2) + questionsPerDifficulty;

        int result = (totalPoints * 100) / possibleTotalPoints;

        examStudent.setResult(result);
        examStudent.setReinforcements(getReinforcements(result,
            examStudent.getExam().getIdTheme()));

        return save(examStudent);
    }

    private String getReinforcements(int result, int idTheme)
    {
        List<Reinforcement> reinforcements = reinforcementService.findByIdTheme(idTheme);
        List<Reinforcement> reinforcementsStudent = new ArrayList<>();

        if (result >= 0 && result <= 33)
        {
            reinforcementsStudent = reinforcements.stream().filter(reinforcement -> reinforcement
                .getIdDifficulty().equals(LOW_DIFFICULTY)).collect(Collectors.toList());
        }
        else if (result >= 34 && result <= 66)
        {
            reinforcementsStudent = reinforcements.stream().filter(reinforcement -> reinforcement
                .getIdDifficulty().equals(MEDIUM_DIFFICULTY)).collect(Collectors.toList());
        }
        else
        {
            reinforcementsStudent = reinforcements.stream().filter(reinforcement -> reinforcement
                .getIdDifficulty().equals(HARD_DIFFICULTY)).collect(Collectors.toList());
        }

        List<Integer> reinforcementIds =
            reinforcementsStudent.stream().map(Reinforcement::getId).collect(Collectors.toList());

        return reinforcementIds.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private int getPointsForAnswer(Answer answer)
    {
        if (answer.getQuestion().getIdDifficulty().equals(LOW_DIFFICULTY))
        {
            return 1;
        }
        else if (answer.getQuestion().getIdDifficulty().equals(MEDIUM_DIFFICULTY))
        {
            return 2;
        }
        else
        {
            return 3;
        }
    }

    public ExamStudentReinforcementsDTO reinforcementsStudentExam(Integer examStudentId)
    {
        ExamStudent examStudent = findById(examStudentId);

        ExamStudentReinforcementsDTO examStudentReinforcementsDTO =
            new ExamStudentReinforcementsDTO();
        examStudentReinforcementsDTO.setId(examStudent.getId());
        examStudentReinforcementsDTO.setIdExam(examStudent.getIdExam());
        examStudentReinforcementsDTO.setIdStudent(examStudent.getIdStudent());
        examStudentReinforcementsDTO.setArea(examStudent.getExam().getArea());

        List<Integer> reinforcementsIds = Arrays.stream(examStudent.getReinforcements()
            .split("\\s*,\\s*")).map(Integer::parseInt).collect(Collectors.toList());

        List<Reinforcement> reinforcements = new ArrayList<>();

        reinforcementsIds.forEach(reinforcementId -> {
            Reinforcement reinforcement = reinforcementService.findById(reinforcementId);
            reinforcements.add(reinforcement);
        });

        examStudentReinforcementsDTO.setReinforcements(reinforcements);

        return examStudentReinforcementsDTO;
    }
}
