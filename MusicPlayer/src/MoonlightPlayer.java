import javax.sound.midi.*;
import java.util.ArrayList;


public class MoonlightPlayer {


    private void addMoonlightNotes(ArrayList<Note> score) {
        int tripletStep = 8;
        int barLength = 96;


        long time = 0;

        score.add(new Note(37, time, barLength, 60));
        score.add(new Note(49, time, barLength, 60));


        for (int i = 0; i < 4; i++) {
            score.add(new Note(56, time, tripletStep, 45));
            time += tripletStep;
            score.add(new Note(61, time, tripletStep, 45));
            time += tripletStep;
            score.add(new Note(64, time, tripletStep, 45));
            time += tripletStep;
        }

        score.add(new Note(35, time, barLength, 60));
        score.add(new Note(47, time, barLength, 60));


        for (int i = 0; i < 4; i++) {
            score.add(new Note(56, time, tripletStep, 45));
            time += tripletStep;
            score.add(new Note(61, time, tripletStep, 45));
            time += tripletStep;
            score.add(new Note(64, time, tripletStep, 45));
            time += tripletStep;
        }
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

                sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
                sequencer.setLoopStartPoint(0);
                sequencer.setLoopEndPoint(-1);

                sequencer.start();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
