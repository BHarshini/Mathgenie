package co.mathgenie.InterfacePackage;

public interface NextButtonClickListener
{

    void onNextButtonClick(int position);

    default void SelectedAnswer(String SelectedAnswer,String correctAnswer,int position,int rating)
    {


    }

    default  void onBackToHomeButtonPressed()
    {

    }

}
