package com.monpro.file;

import java.util.*;

public class FileSharing {
    private PriorityQueue<Integer> ids = new PriorityQueue<>();
    private TreeMap<Integer, Set<Integer>> idToFiles = new TreeMap<>();

    public FileSharing() {
        ids.add(1);
    }

    public int join(List<Integer> ownedChunks) {
        if(ids.isEmpty()) {
            return -1;
        }
        int id = ids.poll();
        if(ids.isEmpty()) {
            ids.add(id + 1);
        }
        idToFiles.put(id, new HashSet<>(ownedChunks));
        return id;
    }

    public void leave(int userID) {
        ids.add(userID);
        idToFiles.remove(userID);
    }

    public List<Integer> request(int userID, int chunkID) {
        List<Integer> result = new ArrayList<>();
        for(Integer id: idToFiles.keySet()) {
            if(idToFiles.get(id).contains(chunkID)) {
                result.add(id);
            }
        }
        if(result.size() != 0) {
            idToFiles.get(userID).add(chunkID);
        }
        return result;
    }
}
