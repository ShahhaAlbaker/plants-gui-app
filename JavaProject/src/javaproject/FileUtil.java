/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author DELL
 */
public class FileUtil {



    // open save file dialog
    public static File chooseSaveFile(java.awt.Component parent, String ext, String desc, String defaultName) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save File");
        chooser.setSelectedFile(new File(defaultName));
        chooser.setFileFilter(new FileNameExtensionFilter(desc, ext));
        int result = chooser.showSaveDialog(parent); //يفتح نافذة الحفظ
        if (result == JFileChooser.APPROVE_OPTION) return chooser.getSelectedFile();
        return null;
    }

    // open file open dialog
    public static File chooseOpenFile(java.awt.Component parent, String ext, String desc) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open File");
        chooser.setFileFilter(new FileNameExtensionFilter(desc, ext));
        int result = chooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) return chooser.getSelectedFile();
        return null; //Cancel
    }

    // export plants to CSV using Formatter
    public static void exportPlantsCSV(List<Plant> plants, File file) throws IOException {
        Formatter formatter = new Formatter(file);
        formatter.format("Plant_ID,Plant_Name,Scientific_Name,Plant_Type,Description,Admin_ID%n");
        for (Plant p : plants) {
            formatter.format("%d,%s,%s,%s,%s,%d%n",
                p.getPlantId(),
                p.getPlantName(),
                p.getScientificName(),
                p.getPlantType(),
                p.getDescription(),
                p.getAdminId());
        }
        formatter.close();
    }

    // import plants from CSV using Scanner
    public static List<String[]> importPlantsCSV(File file) throws IOException {
        List<String[]> rows = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) scanner.nextLine(); // skip header
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                rows.add(line.split(",", -1));
            }
        }
        scanner.close();
        return rows;
    }

    // export diseases to CSV using Formatter
    public static void exportDiseasesCSV(List<Disease> diseases, File file) throws IOException {
        Formatter formatter = new Formatter(file);
        formatter.format("Disease_ID,Disease_Name,Symptoms,Treatment,Plant_ID%n");
        for (Disease d : diseases) {
            formatter.format("%d,%s,%s,%s,%d%n",
                d.getDiseaseId(),
                d.getDiseaseName(),
                d.getSymptoms(),
                d.getTreatment(),
                d.getPlantId());
        }
        formatter.close();
    }

    // serialize plants to .ser file
    public static void serializePlants(List<Plant> plants, File file) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(plants);
        oos.close();
    }

    // deserialize plants from .ser file
    public static List<Plant> deserializePlants(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        List<Plant> plants = (List<Plant>) ois.readObject();
        ois.close();
        return plants;
    }

    
}
