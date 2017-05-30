/**
 * This class extends Question. It can use the constructor
 * and the methods of the super class to eliminate repetative code.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// A question on a test that has a definitive answer

class MultipleChoice extends Question
{
    private String [] possibleAnswers;
    private String correctAnswer;
    private String questionText;
    
    public MultipleChoice(String qT,int p, int d, int aS, String [] pA, String cA) 
    {
        super(qT, p, d, aS);
        possibleAnswers = pA;
        correctAnswer = cA;
    }
     
    public String getQuestionText()
    {
        //String output = "A) " + possibleAnswers[0] + "\n\n" + "B) " + possibleAnswers[1] + "\n\n" + "C) " + possibleAnswers[2] + "\n\n" + "D) " + possibleAnswers[3] + "\n\n" + "E) " + possibleAnswers[4] + "\n\n";
        String output = "";
        int length = possibleAnswers.length;
        String[] abcde = {"A) ", "B) ", "C) ", "D) ", "E) "};
        for(int n = 0; n< length; n++)
        {
            output += abcde[n] + possibleAnswers[n] + "\n\n";
        }
        
        return super.toString() + "\n\n" + output;

    }
    
    //Not using this
   /* public String answerKey()
     {
        String output = "";
        for(String s: correctAnswer)
            output += s + "/n";
        
        return super.toString() + "/n" + output;
    }
        */
    public String getAnswer()
    {
        return correctAnswer;
    }
}
