public class PlaylistNode {
    int songCode;
    String songName;
    PlaylistNode next;
 
    public PlaylistNode(int songCode, String songName) {
        this.songCode = songCode;
        this.songName = songName;
        this.next = null;
    }
}
 