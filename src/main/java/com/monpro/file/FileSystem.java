package com.monpro.file;

public class FileSystem {
    Path root;
    public FileSystem() {
        root = new Path();

    }

    public boolean createPath(String path, int value) {
        String[] strs = path.split("/");
        Path cur = root;
        for(int i = 1; i < strs.length; i++) {
            if(!cur.containsKey(strs[i])) {
                if(i != strs.length - 1) {
                    return false;
                } else {
                    cur.put(strs[i]);
                }
            }
            cur = cur.get(strs[i]);
        }

        // get down to the leaf node
        if(cur.hasValue()) {
            return false;
        } else {
            cur.setValue(value);
            return true;
        }
    }

    public int get(String path) {
        String[] strs = path.split("/");
        Path cur = root;
        for(int i = 1; i < strs.length; i++) {
            if(!cur.containsKey(strs[i])) {
                return -1;
            }
            cur = cur.get(strs[i]);
        }
        return cur.getValue();
    }
}
