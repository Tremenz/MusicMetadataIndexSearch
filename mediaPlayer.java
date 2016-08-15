import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.embed.swt.FXCanvas;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;

public class mediaPlayer {	
public static void main(String[] args) throws UnsupportedEncodingException {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    shell.setText("Play this video app");
    FXCanvas canvas = new FXCanvas(shell, SWT.NONE);
    Group group = new Group();
    Scene scene = new Scene(group);
    canvas.setScene(scene);
    shell.open();
    ArrayList<File> playlist = new ArrayList<File>();
    playlist.add(new File("/Users/Tremenz/Downloads/The Chainsmokers ft Halsey - Closer (Lyric Video) (Clean) (Single) (HD).mp4"));
    List<MediaPlayer> players = new ArrayList<>();
    for(File mediaFile:playlist){
    	Media media = new Media(mediaFile.toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(media);
    	players.add(mediaPlayer);	
    }
    MediaView mediaView = new MediaView(players.get(0));
	mediaView.setMediaPlayer(players.get(0));
    mediaView.getMediaPlayer().play();
    ((Group) scene.getRoot()).getChildren().add(mediaView);
    for (int i = 0; i < players.size(); i++) {
    	final MediaPlayer player     = players.get(i);
    	final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
    		player.setOnEndOfMedia(new Runnable() {
    		@Override public void run() {
    			player.stop();
    		    mediaView.setMediaPlayer(nextPlayer);
    		    nextPlayer.play();
    		    }
    		});
    	}
    while (!shell.isDisposed()) {
        if (!display.readAndDispatch()) display.sleep();
    }
    display.dispose();	
	}
}