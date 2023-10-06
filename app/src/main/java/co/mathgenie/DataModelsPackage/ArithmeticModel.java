package co.mathgenie.DataModelsPackage;

public class ArithmeticModel
{

    private String text;
    private int drawable;

    public ArithmeticModel(String text, int drawable) {
        this.text = text;
        this.drawable = drawable;
    }

    public String getText() {
        return text;
    }

    public int getDrawable() {
        return drawable;
    }

    public boolean hasDrawable() {
        return drawable != 0;
    }
}
