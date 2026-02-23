public class Note {
    int pitch;
    long startTick;
    int duration;
    int velocity;


    public Note(int pitch, long startTick, int duration, int velocity){
        this.pitch =pitch;
        this.startTick =startTick;
        this.duration =duration;
        this.velocity =velocity;
    }



    public int getPitch(){return pitch;}
    public long getStartTick(){return startTick; }
    public int getDuration() { return duration; }
    public int getVelocity() { return velocity; }
}