package edu.ktu.guessthenumber;

public class PersonData {
    private int ID;

    private String name;
    private String difficulty;
    private int age;

    private int guessedNumber;
    private int turnsCount;

    //tuscias konstruktorius
    public PersonData()
    {
        this.ID = 0;
        this.name = "Name";
        this.difficulty = "0";
        this.age = 1;
        this.guessedNumber = 0;
        this.turnsCount = 0;
    }

    //konstruktorius
    public PersonData(int ID, String name, String difficulty, int age, int guessedNumber, int turnsCount)
    {
        this.ID = ID;
        this.name = name;
        this.difficulty = difficulty;
        this.age = age;
        this.guessedNumber = guessedNumber;
        this.turnsCount = turnsCount;
    }

    public int getID() {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getGuessedNumber() { return  guessedNumber; }

    public int getTurnsCount() { return turnsCount; }


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setGuessedNumber(int guessedNumber) { this.guessedNumber = guessedNumber; }

    public void setTurnsCount(int turnsCount) { this.turnsCount = turnsCount; }
}
