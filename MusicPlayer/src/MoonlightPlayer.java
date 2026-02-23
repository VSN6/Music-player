import javax.sound.midi.*;
import java.util.ArrayList;


public class MoonlightPlayer {


    private void addMoonlightNotes(ArrayList<Note> score) {
        score.add(new Note(56, 0, 8, 50));
        score.add(new Note(61, 8, 8, 50));
        score.add(new Note(64, 16, 8, 50));

        score.add(new Note(56, 24, 8, 50));
        score.add(new Note(61, 32, 8, 50));
        score.add(new Note(64, 40, 8, 50));


        score.add(new Note(37, 0, 96, 60));
        score.add(new Note(49, 0, 96, 60));
    }

    private MidiEvent makeEvent(int command, int pitch, int velocity, long tick) throws Exception {
        ShortMessage message = new ShortMessage();
        message.setMessage(command, 0, pitch, velocity);
        return new MidiEvent(message, tick);
    }

        void startPlaying() {
            try {
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.open();

                Sequence sequence = new Sequence(Sequence.PPQ, 24);
                Track track = sequence.createTrack();

                ArrayList<Note> score = new ArrayList<>();
                addMoonlightNotes(score);
                for (Note n : score) {

                    track.add(makeEvent(ShortMessage.NOTE_ON, n.getPitch(), n.getVelocity(), n.getStartTick()));
                    track.add(makeEvent(ShortMessage.NOTE_OFF, n.getPitch(), n.getVelocity(), n.getStartTick() + n.getDuration()));
                }
                sequencer.setSequence(sequence);
                sequencer.setTempoInBPM(50);
                sequencer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
