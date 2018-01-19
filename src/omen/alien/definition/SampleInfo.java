package omen.alien.definition;

public class SampleInfo {

    public Integer bitsPerSample;   // 16
    public Integer blkSize;         // 4096
    public Integer sampleRate;      // 44100
    public Integer byteRate;        // 45328
    public String format;           // ".wav"
    public Long created;            // timestamp of creation
    public Integer channels;        // 1 = mono, 2 = stereo
    public Integer blocks;          // var number of block
    public Float duration;          // var number of seconds
    public Integer frames;          // var number of frames
    public Integer size;            // var number of bytes
    public Integer maxWidth;        // max pixel width for "audiowaveform"
    public Float maxZoom;           // max zoom level for "audiowaveform"

}
