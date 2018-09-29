package questions;


import mainClasses.Sections;

import java.util.ArrayList;
import java.util.List;

public class ListToSection {

    private List<Sections> sectionsList(List<Question> questionList){
        List<Sections> sections=new ArrayList<>();
        for (Question question : questionList){
            Sections section=null;
            for (Sections section1:sections){
                if(section1.getSectionID().equals(question.getSectionID())){
                    section=section1;
                    break;
                }
            }
            if (section==null){
                section=new Sections();
            }
            section.setSectionID(question.getSectionID());
            Question question1=new Question();
            question1.setExamID(question.getExamID());
            question1.setQuestionID(question.getQuestionID());
            question1.setSectionID(question.getSectionID());
            question1.setQuestionName(question.getQuestionName());
            question1.setQuestionType(question.getQuestionType());
            question1.setCorrectoption(question.getCorrectoption());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            section.addQuestion(question1);
            int index=sections.indexOf(section);
            if (index==-1)
            sections.add(section);
            else sections.add(index,section);
        }
        return sections;
    }
    private List<Sections> sectionsListToList(List<Sections> sections1,List<Sections> sections2){
        int i=0;
        for (Sections sections:sections1){
            Sections tempSection=null;
            Sections temp=sections1.get(i);i++;
            int j=0;
            for (Sections sections3:sections2){
                if (sections3.getSectionID().equals(sections.getSectionID())){
                    tempSection=sections2.get(j);
                    j++;
                    break;
                }
            }
            temp.setSectionName(tempSection.getSectionName());
            temp.setSectionTime(tempSection.getSectionTime());
            temp.setQuestions(tempSection.getQuestions());
        }
        return sections1;
    }

}
