
import com.sun.jna.NativeLibrary;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.plaf.metal.MetalSliderUI;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


class PlayVideo extends JFrame{
    
    private EmbeddedMediaPlayerComponent player;
    private JButton pause,stop;
    private JPanel panel;
    private ImageIcon i1,i2,i3;
    private JSlider videoSlider,audioSlider;
    private long videoLength;
    
    public PlayVideo(int h, int v, String file) {
        super("Your Video");
        setSize(h,v);
        
        setPanel(h);
        setFrame(file,h,v);
        actionListeners();
        
        setUndecorated(true);
        setVisible(true);
        player.getMediaPlayer().playMedia(file);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                player.getMediaPlayer().stop();
                dispose();
            }
        });
        
    }
    
    private void setPanel(int h){
        
        i1=new ImageIcon("pause");
        i2=new ImageIcon("stop");
        i3=new ImageIcon("play");
        
        pause=new JButton();
        stop=new JButton();
        
        pause.setContentAreaFilled(false);
        stop.setContentAreaFilled(false);
        
        pause.setBorderPainted(false);
        stop.setBorderPainted(false);
        
        pause.setIcon(i1);stop.setIcon(i2);
        
        videoSlider=new JSlider();
        videoSlider.setBackground(new Color(0,0,0));
        videoSlider.setValue(0);
        videoSlider.setMinimum(0);
        videoSlider.setPreferredSize(new Dimension(500,20));
        
        audioSlider=new JSlider();
        audioSlider.setBackground(new Color(0,0,0));
        audioSlider.setValue(0);
        audioSlider.setMinimum(0);
        audioSlider.setMaximum(100);
        audioSlider.setPreferredSize(new Dimension(20,100));
        audioSlider.setOrientation(JSlider.VERTICAL);
        
        panel=new JPanel();
        panel.setMinimumSize(new Dimension(h,i1.getIconHeight()));
        panel.setMaximumSize(new Dimension(h,i1.getIconHeight()));
        panel.setBackground(new Color(0,0,0));
        //panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        panel.add(pause);
        panel.add(stop);
        panel.add(videoSlider);
        panel.add(audioSlider);
        
        pause.setMinimumSize(new Dimension(i1.getIconWidth(),i1.getIconHeight()));
        pause.setMaximumSize(new Dimension(i1.getIconWidth(),i1.getIconHeight()));
        stop.setMinimumSize(new Dimension(i1.getIconWidth(),i1.getIconHeight()));
        stop.setMaximumSize(new Dimension(i1.getIconWidth(),i1.getIconHeight()));
        
    }
    
    private void setFrame(String file,int h,int v){
        
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"Libraries//x64");
        player=new EmbeddedMediaPlayerComponent();
        
        File f=new File(file);
        file=f.getAbsoluteFile().toString();
        
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(player);
        add(panel);
        
    }
    
    private void actionListeners(){
        
        player.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListener() {
            public void mediaChanged(MediaPlayer mp, libvlc_media_t l, String string) {}
            public void opening(MediaPlayer mp) {}
            public void buffering(MediaPlayer mp, float f) {}
            public void playing(MediaPlayer mp) {
                videoLength = player.getMediaPlayer().getLength();
                videoLength = videoLength/1000;
                videoSlider.setMaximum((int)videoLength);
            }
            public void paused(MediaPlayer mp) {}
            public void stopped(MediaPlayer mp) {}
            public void forward(MediaPlayer mp) {}
            public void backward(MediaPlayer mp) {}
            public void finished(MediaPlayer mp) {}
            public void timeChanged(MediaPlayer mp, long l) {
                int v=(int)player.getMediaPlayer().getTime();
                v=v/1000;
                videoSlider.setValue(v);
                
                int a=(int)player.getMediaPlayer().getVolume();
                audioSlider.setValue(a);
            }
            public void positionChanged(MediaPlayer mp, float f) {}
            public void seekableChanged(MediaPlayer mp, int i) {}
            public void pausableChanged(MediaPlayer mp, int i) {}
            public void titleChanged(MediaPlayer mp, int i) {}
            public void snapshotTaken(MediaPlayer mp, String string) {}
            public void lengthChanged(MediaPlayer mp, long l) {}
            public void videoOutput(MediaPlayer mp, int i) {}
            public void error(MediaPlayer mp) {}
            public void mediaMetaChanged(MediaPlayer mp, int i) {}
            public void mediaSubItemAdded(MediaPlayer mp, libvlc_media_t l) {}
            public void mediaDurationChanged(MediaPlayer mp, long l) {}
            public void mediaParsedChanged(MediaPlayer mp, int i) {}
            public void mediaFreed(MediaPlayer mp) {}
            public void mediaStateChanged(MediaPlayer mp, int i) {}
            public void newMedia(MediaPlayer mp) {}
            public void subItemPlayed(MediaPlayer mp, int i) {}
            public void subItemFinished(MediaPlayer mp, int i) {}
            public void endOfSubItems(MediaPlayer mp) {}
        });
        
        videoSlider.setUI(new MetalSliderUI(){
            protected void scrollDueToClickInTrack(int direction) {
                int value=videoSlider.getValue();
                
                if (videoSlider.getOrientation() == JSlider.HORIZONTAL) {
                    value = this.valueForXPosition(videoSlider.getMousePosition().x);
                } 
                else if (videoSlider.getOrientation() == JSlider.VERTICAL) {
                    value = this.valueForYPosition(videoSlider.getMousePosition().y);
                }
                
                player.getMediaPlayer().setTime(value*1000);
            }
        });
        
        pause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                player.getMediaPlayer().pause();
                if(pause.getIcon()==i1)pause.setIcon(i3);
                else pause.setIcon(i1);
            }
        });
        
        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                player.getMediaPlayer().stop();
                dispose();
            }
        });
        
        audioSlider.setUI(new MetalSliderUI(){
            protected void scrollDueToClickInTrack(int direction) {
                int value=audioSlider.getValue();
                
                if (audioSlider.getOrientation() == JSlider.HORIZONTAL) {
                    value = this.valueForXPosition(audioSlider.getMousePosition().x);
                } 
                else if (audioSlider.getOrientation() == JSlider.VERTICAL) {
                    value = this.valueForYPosition(audioSlider.getMousePosition().y);
                }
                
                audioSlider.setValue(value);
                player.getMediaPlayer().setVolume(value);
                
            }
        });
        
    }
    
}
