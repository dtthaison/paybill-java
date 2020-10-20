package vn.momo.paybill.repository;

import vn.momo.paybill.model.ModelInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BaseRepository implements RepositoryInterface {
    private static String dbPath = "/Users/doanson/momo_paybill_db.txt";

    public void save(ModelInterface record) throws Exception {
        boolean isPersisted = record.isPersisted();
        if (!isPersisted) {
            record.setId(System.currentTimeMillis());
        }

        write(record.table(), record.getId(), record.insertValue(), isPersisted);
    }

    public void clear() throws Exception {
        dbFile().delete();
    }

    protected List<String[]> scan(String table) throws Exception {
        File f = dbFile();

        BufferedReader br = new BufferedReader(new FileReader(f));
        List<String[]> result = new ArrayList<>();
        for(String line; (line = br.readLine()) != null; ) {
            if (line.startsWith(table)) {
                result.add(line.split(","));
            }
        }
        br.close();
        return result;
    }

    protected void write(String table, long id, String content, boolean isPersisted) throws Exception {
        File f = dbFile();

        BufferedWriter bw;
        String prefix = table+","+id+",";
        if (!isPersisted) {
            bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile(), true));
            bw.write(prefix+content+"\r\n");
        } else {
            File tempFile = new File(dbPath+"tmp");
            bw = new BufferedWriter(new FileWriter(tempFile.getAbsoluteFile(), true));

            BufferedReader br = new BufferedReader(new FileReader(f));
            boolean isEdited = false;
            for(String line; (line = br.readLine()) != null; ) {
                if (line.startsWith(prefix)) {
                    bw.write(prefix+content+"\r\n");
                    isEdited = true;
                } else {
                    bw.write(line+"\r\n");
                }
            }
            if(!isEdited) {
                bw.write(prefix+content+"\r\n");
            }
            br.close();
            f.delete();
            tempFile.renameTo(f);
        }

        bw.close();
    }

    private File dbFile() throws Exception {
        File f = new File(dbPath);
        if (!f.exists()) {
            File directory = new File(f.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            f.createNewFile();
        }

        return f;
    }
}
