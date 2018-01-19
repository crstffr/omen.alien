package omen.alien.definition;

public class SampleData {
    public String id;          // index in db
    public String name;        // user provided name
    public String filepath;    // absolute path to the sample wav file
    public String filename;    // initial auto incremented filename (aka foldername)
    public SampleInfo info;    // object containing all the details of the sample file
}
