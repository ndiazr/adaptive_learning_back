package com.adaptativelearning.examstudent;

import com.adaptativelearning.base.BaseService;
import com.adaptativelearning.exam.Exam;
import com.adaptativelearning.exam.ExamService;
import com.adaptativelearning.parameter.Parameter;
import com.adaptativelearning.parameter.ParameterService;
import com.adaptativelearning.question.Question;
import com.adaptativelearning.question.QuestionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamStudentService extends BaseService<ExamStudent, Integer>
{
    private static final String ASSIGNED_STATE = "assigned";
    private static final String IN_PROGRESS_STATE = "in_progress";
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
    private ExamStudentRepository examStudentRepository;

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

    public Object takeStudentExam(Integer examStudentId)
    {
        ExamStudent examStudent = findById(examStudentId);
        examStudent.setState(IN_PROGRESS_STATE);
        examStudent = save(examStudent);

        ExamStudentTakeDTO examStudentTakeDTO = new ExamStudentTakeDTO();
        examStudentTakeDTO.setId(examStudent.getId());
        examStudentTakeDTO.setIdExam(examStudent.getIdExam());
        examStudentTakeDTO.setIdStudent(examStudent.getIdStudent());
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
}
