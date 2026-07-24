public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        head = null;
    }

    public boolean addLast(int songCode, String songName) {
        if (contains(songCode)) {
            System.out.println("歌曲代碼 " + songCode + " 已存在，無法新增");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(songCode, songName);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        return true;
    }

    public boolean contains(int songCode) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songCode == songCode) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public PlaylistNode search(int songCode) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songCode == songCode) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean removeSong(int songCode) {
        if (head == null) {
            return false;
        }

        if (head.songCode == songCode) {
            head = head.next;
            return true;
        }

        PlaylistNode current = head;
        while (current.next != null) {
            if (current.next.songCode == songCode) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放清單是空的");
            return;
        }

        PlaylistNode current = head;
        String result = "";
        while (current != null) {
            result = result + "[" + current.songCode + "]" + current.songName;
            if (current.next != null) {
                result = result + " -> ";
            }
            current = current.next;
        }
        System.out.println(result);
    }
}
