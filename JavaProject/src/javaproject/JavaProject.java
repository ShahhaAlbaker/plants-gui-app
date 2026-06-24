package javaproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class JavaProject {

    //style
    static Color GREEN_DARK = new Color(39, 110, 60);
    static Color GREEN_MEDIUM = new Color(76, 153, 100);
    static Color GREEN_LIGHT = new Color(232, 245, 233);
    static Color WHITE = Color.WHITE;
    static Color TEXT_DARK = new Color(33, 33, 33);
    static Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 14);
    static Font FONT_NORMAL = new Font("SansSerif", Font.PLAIN, 13);

    // var from persom 
    static Person currentUser;

    public static void main(String[] args) {

        showLoginScreen();
    }

    // styl Button
    static JButton styledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(WHITE);
        btn.setFont(FONT_NORMAL);
        btn.setPreferredSize(new Dimension(110, 32));
        return btn;
    }

    // style Field
    static JTextField styledField() {
        JTextField field = new JTextField();
        field.setFont(FONT_NORMAL);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GREEN_MEDIUM, 1),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)));
        return field;
    }

    // Style table
    static void styleTable(JTable table) {
        table.setFont(FONT_NORMAL);
        table.setRowHeight(28);
        table.setSelectionBackground(GREEN_MEDIUM);
        table.setSelectionForeground(WHITE);
        table.getTableHeader().setBackground(GREEN_DARK);
        table.getTableHeader().setForeground(WHITE);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.setBackground(WHITE);
        table.setForeground(TEXT_DARK);
    }

    // SCREEN 1- Login Screen
    static void showLoginScreen() {

        JFrame frame = new JFrame("🌿 Plant Reference System");
        frame.setSize(400, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(GREEN_LIGHT);

        // header
        JPanel header = new JPanel();
        header.setBackground(GREEN_DARK);
        JLabel title = new JLabel("🌿 Plant Reference System");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(WHITE);
        header.add(title);

        // form
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 12));
        form.setBackground(GREEN_LIGHT);
        form.setBorder(new EmptyBorder(25, 40, 10, 40));

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(FONT_NORMAL);
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"User", "Admin"});
        roleBox.setFont(FONT_NORMAL);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(FONT_NORMAL);
        JTextField emailField = styledField();

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(FONT_NORMAL);
        JPasswordField passField = new JPasswordField();
        passField.setFont(FONT_NORMAL);
        passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GREEN_MEDIUM, 1),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)));

        JLabel msgLabel = new JLabel(" ");
        msgLabel.setForeground(Color.RED);
        msgLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

        form.add(roleLabel);
        form.add(roleBox);
        form.add(emailLabel);
        form.add(emailField);
        form.add(passLabel);
        form.add(passField);
        form.add(msgLabel);

        // login button
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(GREEN_LIGHT);
        btnPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        JButton loginBtn = styledButton("Login", GREEN_DARK);
        loginBtn.setPreferredSize(new Dimension(160, 36));
        btnPanel.add(loginBtn);

        frame.setLayout(new BorderLayout());
        frame.add(header, BorderLayout.NORTH);
        frame.add(form, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String pass = new String(passField.getPassword()).trim();
                String role = (String) roleBox.getSelectedItem(); // اللي فوق

                if (email.isEmpty() || pass.isEmpty()) {
                    msgLabel.setText("Please fill all fields.");
                    return;
                }
                try {
                    UserDAO dao = new UserDAO();
                    Person user = null;
                    if (role.equals("Admin")) {
                        user = dao.loginAdmin(email, pass);
                    } else {
                        user = dao.loginUser(email, pass);
                    }
                    if (user != null) {
                        currentUser = user;
                        frame.dispose();
                        showMainScreen(user);
                    } else {
                        msgLabel.setText("Wrong email or password.");
                    }
                } catch (Exception ex) {
                    msgLabel.setText("Error: " + ex.getMessage());
                }
            }
        });
    }

    // SCREEN 2 Main Screen
    static void showMainScreen(Person user) {

        JFrame frame = new JFrame("🌿 Plant Reference System");
        frame.setSize(950, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(GREEN_DARK);
        topBar.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel welcomeLabel = new JLabel("🌿  Welcome, " + user.getUsername() + "   |   " + user.getRole()); // person class
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        welcomeLabel.setForeground(WHITE);

        JButton logoutBtn = styledButton("Logout", new Color(180, 60, 60));
        logoutBtn.setPreferredSize(new Dimension(90, 30));
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showLoginScreen();
            }

        });

        topBar.add(welcomeLabel, BorderLayout.WEST);
        topBar.add(logoutBtn, BorderLayout.EAST);

        // tabs  🌱 Plants | 💧 Care Guides | 🦠 Diseases | ⚙️ Admin Panel
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.BOLD, 13));
        tabs.setBackground(GREEN_LIGHT);
        tabs.addTab("🌱 Plants", buildPlantTab(user));
        tabs.addTab("💧 Care Guides", buildCareTab());
        tabs.addTab("🦠 Diseases", buildDiseaseTab());

        if (user instanceof Admin) {
            tabs.addTab("⚙️ Admin Panel", buildAdminTab((Admin) user, tabs));
        }

        frame.add(topBar, BorderLayout.NORTH);
        frame.add(tabs, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // TAB 1Plants
    static JPanel buildPlantTab(Person user) {

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(GREEN_LIGHT);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        searchPanel.setBackground(GREEN_LIGHT);
        JTextField searchField = styledField();
        searchField.setPreferredSize(new Dimension(200, 30));
        JButton searchBtn = styledButton("Search", GREEN_MEDIUM);
        JButton showAllBtn = styledButton("Show All", GREEN_DARK);
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(showAllBtn);

        String[] columns = {"ID", "Plant Name", "Scientific Name", "Type", "Description"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) { //anonymous class 
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        styleTable(table);

        loadPlantsToTable(tableModel, ""); // an تملي الجدول 

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPlantsToTable(tableModel, searchField.getText().trim());
            }
        });
        showAllBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                loadPlantsToTable(tableModel, "");
            }
        });

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    static void loadPlantsToTable(DefaultTableModel model, String keyword) {
        try {
            PlantDAO dao = new PlantDAO();
            List<Plant> list; // type
            if (keyword.isEmpty()) {
                list = dao.getAllPlants();
            } else {
                list = dao.searchByName(keyword);
            }
            model.setRowCount(0);
            for (Plant p : list) {
                model.addRow(new Object[]{
                    p.getPlantId(), p.getPlantName(),
                    p.getScientificName(), p.getPlantType(), p.getDescription()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
        }
    }

    // TAB 2 Care Guides
    static JPanel buildCareTab() {

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(GREEN_LIGHT);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        String[] columns = {"Care ID", "Watering", "Sunlight", "Soil Type", "Temperature", "Plant ID"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        JTable table = new JTable(model);
        styleTable(table);

        try {
            CareGuideDAO dao = new CareGuideDAO();
            List<CareGuide> list = dao.getAllCareGuides();
            for (CareGuide cg : list) {
                model.addRow(new Object[]{
                    cg.getCareId(), cg.getWateringFrequency(),
                    cg.getSunlightNeeds(), cg.getSoilType(),
                    cg.getTemperature(), cg.getPlantId()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    // TAB 3 Diseases
    static JPanel buildDiseaseTab() {

        JPanel panel = new JPanel(new BorderLayout(5, 5)); //
        panel.setBackground(GREEN_LIGHT);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        topPanel.setBackground(GREEN_LIGHT);
        JTextField symptomField = styledField();
        symptomField.setPreferredSize(new Dimension(200, 30));
        JButton searchBtn = styledButton("Search", GREEN_MEDIUM);
        JButton showAllBtn = styledButton("Show All", GREEN_DARK);
        JButton exportBtn = styledButton("Export CSV", new Color(70, 130, 180));
        JButton saveSerBtn = styledButton("Save .ser", new Color(130, 90, 160));
        topPanel.add(new JLabel("Symptoms: "));
        topPanel.add(symptomField);
        topPanel.add(searchBtn);
        topPanel.add(showAllBtn);
        topPanel.add(exportBtn);
        topPanel.add(saveSerBtn);

        String[] columns = {"ID", "Disease Name", "Symptoms", "Treatment", "Plant ID"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        JTable table = new JTable(model);
        styleTable(table);

        final List<Disease>[] allDiseases = new List[1];
        try {
            DiseaseDAO dao = new DiseaseDAO();
            allDiseases[0] = dao.getAllDiseases();
            for (Disease d : allDiseases[0]) {
                model.addRow(new Object[]{
                    d.getDiseaseId(), d.getDiseaseName(),
                    d.getSymptoms(), d.getTreatment(), d.getPlantId()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
        }

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kw = symptomField.getText().trim().toLowerCase();
                if (kw.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter symptoms first.");
                    return;
                }
                model.setRowCount(0);
                if (allDiseases[0] != null) {
                    for (Disease d : allDiseases[0]) {
                        if (d.getSymptoms().toLowerCase().contains(kw)
                                || d.getDiseaseName().toLowerCase().contains(kw)) {
                            model.addRow(new Object[]{
                                d.getDiseaseId(), d.getDiseaseName(),
                                d.getSymptoms(), d.getTreatment(), d.getPlantId()
                            });
                        }
                    }
                }
            }
        });

        showAllBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                symptomField.setText("");
                model.setRowCount(0);
                if (allDiseases[0] != null) {
                    for (Disease d : allDiseases[0]) {
                        model.addRow(new Object[]{
                            d.getDiseaseId(), d.getDiseaseName(),
                            d.getSymptoms(), d.getTreatment(), d.getPlantId()
                        });
                    }
                }
            }
        });

        exportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.chooseSaveFile(null, "csv", "CSV Files", "diseases_export.csv");
                if (file != null && allDiseases[0] != null) {
                    try {
                        FileUtil.exportDiseasesCSV(allDiseases[0], file);
                        JOptionPane.showMessageDialog(null, "Exported successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Export error: " + ex.getMessage());
                    }
                }
            }
        });

        saveSerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.chooseSaveFile(null, "ser", "Serialized Files", "diseases.ser");
                if (file != null && allDiseases[0] != null) {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(allDiseases[0]);
                        oos.close();
                        JOptionPane.showMessageDialog(null, "Saved successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Save error: " + ex.getMessage());
                    }
                }
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    // TAB 4 Admin  Panel
    static JPanel buildAdminTab(Admin admin, JTabbedPane tabs) {
// tatil
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(GREEN_LIGHT);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        // form
        JPanel form = new JPanel(new GridLayout(5, 2, 8, 8));
        form.setBackground(GREEN_LIGHT);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(GREEN_MEDIUM, 1), "Plant Form",
                0, 0, FONT_TITLE, GREEN_DARK)); //┌── Plant Form ──┐

        JTextField idField = styledField();
        idField.setEditable(false);
        JTextField nameField = styledField();
        JTextField sciField = styledField();
        JTextField descField = styledField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Flower", "Herb", "Succulent", "Indoor"});
        typeBox.setFont(FONT_NORMAL);

        form.add(new JLabel("ID (auto):"));
        form.add(idField);
        form.add(new JLabel("Plant Name:"));
        form.add(nameField);
        form.add(new JLabel("Scientific Name:"));
        form.add(sciField);
        form.add(new JLabel("Type:"));
        form.add(typeBox);
        form.add(new JLabel("Description:"));
        form.add(descField);

        // buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        btnPanel.setBackground(GREEN_LIGHT);
        JButton addBtn = styledButton("Add", GREEN_DARK);
        JButton updateBtn = styledButton("Update", GREEN_MEDIUM);
        JButton deleteBtn = styledButton("Delete", new Color(180, 60, 60));
        JButton clearBtn = styledButton("Clear", new Color(120, 120, 120));
        JButton exportBtn = styledButton("Export CSV", new Color(70, 130, 180));
        JButton importBtn = styledButton("Import CSV", new Color(70, 130, 180));
        JButton serBtn = styledButton("Serialize", new Color(130, 90, 160));
        JButton deserBtn = styledButton("Load .ser", new Color(130, 90, 160));
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(exportBtn);
        btnPanel.add(importBtn);
        btnPanel.add(serBtn);
        btnPanel.add(deserBtn);

        // table
        String[] columns = {"ID", "Plant Name", "Scientific Name", "Type", "Description"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        JTable table = new JTable(model);
        styleTable(table);

        loadPlantsToTable(model, "");

        table.getSelectionModel().addListSelectionListener(e -> { //Lambda Expression j8 
            int row = table.getSelectedRow();
            if (row >= 0) {
                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                sciField.setText(model.getValueAt(row, 2).toString());
                typeBox.setSelectedItem(model.getValueAt(row, 3).toString());
                descField.setText(model.getValueAt(row, 4) != null ? model.getValueAt(row, 4).toString() : "");
            }
        });

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Plant name is required.");
                    return;
                }
                try {
                    Plant newPlant = makePlant(0, nameField, sciField, descField, typeBox, admin.getId());
                    new PlantDAO().insertPlant(newPlant);
                    JOptionPane.showMessageDialog(null, "Plant added!");
                    clearForm(idField, nameField, sciField, descField, typeBox);
                    loadPlantsToTable(model, "");
                    tabs.setComponentAt(0, buildPlantTab(admin)); //  اي تغيير بينحط لتاب النبات وتاب النبات رقمه 0 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Select a plant first.");
                    return;
                }
                try {
                    int pid = Integer.parseInt(idField.getText().trim());
                    Plant updated = makePlant(pid, nameField, sciField, descField, typeBox, admin.getId());
                    new PlantDAO().updatePlant(updated);
                    JOptionPane.showMessageDialog(null, "Plant updated!");
                    clearForm(idField, nameField, sciField, descField, typeBox);
                    loadPlantsToTable(model, "");
                    tabs.setComponentAt(0, buildPlantTab(admin));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(null, "Select a row to delete.");
                    return;
                }
                String name = model.getValueAt(row, 1).toString();
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Delete plant: " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                try {
                    int pid = Integer.parseInt(model.getValueAt(row, 0).toString());
                    new PlantDAO().deleteWithTransaction(pid);
                    JOptionPane.showMessageDialog(null, "Plant deleted!");
                    clearForm(idField, nameField, sciField, descField, typeBox);
                    loadPlantsToTable(model, "");
                    tabs.setComponentAt(0, buildPlantTab(admin));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm(idField, nameField, sciField, descField, typeBox);
            }
        });

        exportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.chooseSaveFile(null, "csv", "CSV Files", "plants_export.csv");
                if (file == null) {
                    return;
                }
                try {
                    List<Plant> plants = new PlantDAO().getAllPlants();
                    FileUtil.exportPlantsCSV(plants, file);
                    JOptionPane.showMessageDialog(null, "Exported " + plants.size() + " plants!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Export error: " + ex.getMessage());
                }
            }
        });

        importBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.chooseOpenFile(null, "csv", "CSV Files");
                if (file == null) {
                    return;
                }
                try {
                    List<String[]> rows = FileUtil.importPlantsCSV(file);
                    PlantDAO dao = new PlantDAO();
                    int count = 0;
                    for (String[] r : rows) {
                        if (r.length < 5) {
                            continue;
                        }
                        Plant p = new FlowerPlant(0, r[1].trim(), r[2].trim(), r[4].trim(), admin.getId(), "", "", false);
                        if (dao.insertPlant(p) > 0) {
                            count++;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Imported " + count + " plants!");
                    loadPlantsToTable(model, "");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Import error: " + ex.getMessage());
                }
            }
        });

        serBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.chooseSaveFile(null, "ser", "Serialized Files", "plants.ser");
                if (file == null) {
                    return;
                }
                try {
                    List<Plant> plants = new PlantDAO().getAllPlants();
                    FileUtil.serializePlants(plants, file);
                    JOptionPane.showMessageDialog(null, "Serialized " + plants.size() + " plants!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        deserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.chooseOpenFile(null, "ser", "Serialized Files");
                if (file == null) {
                    return;
                }
                try {
                    List<Plant> plants = FileUtil.deserializePlants(file);
                    model.setRowCount(0);
                    for (Plant p : plants) {
                        model.addRow(new Object[]{
                            p.getPlantId(), p.getPlantName(),
                            p.getScientificName(), p.getPlantType(), p.getDescription()
                        });
                    }
                    JOptionPane.showMessageDialog(null, "Loaded " + plants.size() + " plants!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        JPanel northArea = new JPanel(new BorderLayout(0, 6));
        northArea.setBackground(GREEN_LIGHT);
        northArea.add(form, BorderLayout.CENTER);
        northArea.add(btnPanel, BorderLayout.SOUTH);

        panel.add(northArea, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
    // Helpers

    static Plant makePlant(int id, JTextField name, JTextField sci, JTextField desc, JComboBox<String> type, int adminId) //Polymorphism 
    {
        String selected = (String) type.getSelectedItem();
        if (selected.equals("Herb")) {
            return new HerbPlant(id, name.getText(), sci.getText(), desc.getText(), adminId, "", "", "");
        } else if (selected.equals("Succulent")) {
            return new SucculentPlant(id, name.getText(), sci.getText(), desc.getText(), adminId, "", 0, "");
        } else if (selected.equals("Indoor")) {
            return new IndoorPlant(id, name.getText(), sci.getText(), desc.getText(), adminId, "", false, false);
        } else {
            return new FlowerPlant(id, name.getText(), sci.getText(), desc.getText(), adminId, "", "", false);
        }
    }

    static void clearForm(JTextField id, JTextField name, JTextField sci,
            JTextField desc, JComboBox<String> type) {
        id.setText("");
        name.setText("");
        sci.setText("");
        desc.setText("");
        type.setSelectedIndex(0);
    }
}
