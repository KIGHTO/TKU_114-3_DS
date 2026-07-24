public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("新增歌曲 101 - 稻香");
        playlist.addLast(101, "稻香");
        playlist.printPlaylist();

        System.out.println("新增歌曲 102 - 晴天");
        playlist.addLast(102, "晴天");
        playlist.printPlaylist();

        System.out.println("新增歌曲 103 - 演員");
        playlist.addLast(103, "演員");
        playlist.printPlaylist();

        System.out.println("新增重複代碼 101");
        playlist.addLast(101, "重複測試");
        playlist.printPlaylist();

        PlaylistNode found = playlist.search(102);
        if (found == null) {
            System.out.println("搜尋 102：找不到");
        } else {
            System.out.println("搜尋 102：" + found.songName);
        }

        PlaylistNode notFound = playlist.search(999);
        if (notFound == null) {
            System.out.println("搜尋 999：找不到");
        } else {
            System.out.println("搜尋 999：" + notFound.songName);
        }

        System.out.println("刪除第一首（101）");
        playlist.removeSong(101);
        playlist.printPlaylist();

        System.out.println("刪除最後一首（103）");
        playlist.removeSong(103);
        playlist.printPlaylist();

        System.out.println("刪除不存在的代碼 999");
        playlist.removeSong(999);
        playlist.printPlaylist();
    }
}
