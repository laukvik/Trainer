/*
 * ExerciseEditor.java
 *
 * Created on January 3, 2009, 12:54 PM
 */

package org.laukvik.trainer.exercise;

import org.laukvik.trainer.swing.DroppableIcon;

/**
 *
 * @author  morten
 */
public class ExerciseEditorPanel extends javax.swing.JPanel {
    
    /** Creates new form ExerciseEditor */
    public ExerciseEditorPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jTabbedPane1 = new javax.swing.JTabbedPane();
        overviewPanel = new javax.swing.JPanel();
        overviewStartPanel = new javax.swing.JPanel();
        overviewStartIcon = new DroppableIcon("StartIcon");
        overviewNameLabel = new javax.swing.JLabel();
        overviewDescriptionScrollPane = new javax.swing.JScrollPane();
        overviewDescriptionTextArea = new javax.swing.JTextArea();
        visibleCheckbox = new javax.swing.JCheckBox();
        overviewStopPanel = new javax.swing.JPanel();
        overviewStopIcon = new DroppableIcon("StopIcon");
        overviewYouTubeButton = new javax.swing.JButton();
        overviewAuthorHomepageButton = new javax.swing.JButton();
        infoPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextarea = new javax.swing.JTextArea();
        languageLabel = new javax.swing.JLabel();
        languageComboBox = new javax.swing.JComboBox();
        authorPanel = new javax.swing.JPanel();
        authorNameLabel = new javax.swing.JLabel();
        authorNameTextField = new javax.swing.JTextField();
        authorHomepageLabel = new javax.swing.JLabel();
        authorHomepageTextField = new javax.swing.JTextField();
        youTubeLabel = new javax.swing.JLabel();
        youTubeTextField = new javax.swing.JTextField();
        youTubeButton = new javax.swing.JButton();
        authorHomePageButton = new javax.swing.JButton();
        musclesScrollPane = new javax.swing.JScrollPane();
        musclesPanel = new javax.swing.JPanel();
        exampleMuscleCheckbox1 = new javax.swing.JCheckBox();
        exampleMuscleCheckbox2 = new javax.swing.JCheckBox();
        photosScrollPane = new javax.swing.JScrollPane();
        photosPanel = new javax.swing.JPanel();
        examplePhotoPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        overviewStartPanel.setLayout(new java.awt.BorderLayout());

        overviewStartPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        overviewStartPanel.setMaximumSize(new java.awt.Dimension(100, 100));
        overviewStartPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        overviewStartPanel.setPreferredSize(new java.awt.Dimension(100, 100));
        overviewStartIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        overviewStartPanel.add(overviewStartIcon, java.awt.BorderLayout.CENTER);

        overviewNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        overviewNameLabel.setText("Barbell Curl");

        overviewDescriptionTextArea.setColumns(20);
        overviewDescriptionTextArea.setEditable(false);
        overviewDescriptionTextArea.setLineWrap(true);
        overviewDescriptionTextArea.setRows(5);
        overviewDescriptionTextArea.setText("Possibly the best biceps exercise! With your hands shoulder-width apart, grip a barbell with an underhand grip. Stand straight up with your shoulders squared and with your feet shoulder-width apart. Let the bar hang down at arm's length in front of you, with your arms, shoulders and hands in a straight line. WITHOUT leaning back or swinging the weight, curl the bar up toward your chest in an arc. Keep your elbows in the same place and close to your sides. Bring the weight up as high as you can and squeeze the biceps at the top. Lower the weight slowly, resisting all the way down until your arms are nearly straight.");
        overviewDescriptionTextArea.setWrapStyleWord(true);
        overviewDescriptionScrollPane.setViewportView(overviewDescriptionTextArea);

        visibleCheckbox.setText("Visible in charts");
        visibleCheckbox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        visibleCheckbox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        overviewStopPanel.setLayout(new java.awt.BorderLayout());

        overviewStopPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        overviewStopPanel.setMaximumSize(new java.awt.Dimension(100, 100));
        overviewStopPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        overviewStopPanel.setPreferredSize(new java.awt.Dimension(100, 100));
        overviewStopIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        overviewStopPanel.add(overviewStopIcon, java.awt.BorderLayout.CENTER);

        overviewYouTubeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/laukvik/trainer/swing/icons/youtube.png")));

        overviewAuthorHomepageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/laukvik/trainer/swing/icons/tb_shop.png")));
        overviewAuthorHomepageButton.setText("WWW");

        org.jdesktop.layout.GroupLayout overviewPanelLayout = new org.jdesktop.layout.GroupLayout(overviewPanel);
        overviewPanel.setLayout(overviewPanelLayout);
        overviewPanelLayout.setHorizontalGroup(
            overviewPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(overviewPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(overviewYouTubeButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(overviewAuthorHomepageButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(overviewStartPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(overviewStopPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(10, 10, 10)
                .add(overviewPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(visibleCheckbox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .add(overviewNameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .add(overviewDescriptionScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                .addContainerGap())
        );
        overviewPanelLayout.setVerticalGroup(
            overviewPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(overviewNameLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(overviewPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(overviewPanelLayout.createSequentialGroup()
                        .add(overviewStartPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(20, 20, 20)
                        .add(overviewStopPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(overviewYouTubeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(overviewAuthorHomepageButton))
                    .add(overviewDescriptionScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(visibleCheckbox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Oversikt", overviewPanel);

        nameLabel.setLabelFor(nameTextField);
        nameLabel.setText("Name");

        nameTextField.setText("Barbell Curl");

        descriptionLabel.setText("Description");

        descriptionTextarea.setColumns(20);
        descriptionTextarea.setLineWrap(true);
        descriptionTextarea.setRows(5);
        descriptionTextarea.setText("Possibly the best biceps exercise! With your hands shoulder-width apart, grip a barbell with an underhand grip. Stand straight up with your shoulders squared and with your feet shoulder-width apart. Let the bar hang down at arm's length in front of you, with your arms, shoulders and hands in a straight line. WITHOUT leaning back or swinging the weight, curl the bar up toward your chest in an arc. Keep your elbows in the same place and close to your sides. Bring the weight up as high as you can and squeeze the biceps at the top. Lower the weight slowly, resisting all the way down until your arms are nearly straight.");
        descriptionTextarea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descriptionTextarea);

        languageLabel.setText("Language");

        languageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English" }));

        org.jdesktop.layout.GroupLayout infoPanelLayout = new org.jdesktop.layout.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(infoPanelLayout.createSequentialGroup()
                        .add(languageLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(infoPanelLayout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(infoPanelLayout.createSequentialGroup()
                        .add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(nameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                            .add(nameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
                        .addContainerGap())
                    .add(infoPanelLayout.createSequentialGroup()
                        .add(descriptionLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(infoPanelLayout.createSequentialGroup()
                        .add(languageComboBox, 0, 582, Short.MAX_VALUE)
                        .add(29, 29, 29))))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(nameLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(nameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23)
                .add(descriptionLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .add(23, 23, 23)
                .add(languageLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(languageComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jTabbedPane1.addTab("Info", infoPanel);

        authorNameLabel.setText("Forfatter");

        authorNameTextField.setText("Morten Laukvik");

        authorHomepageLabel.setLabelFor(authorHomepageTextField);
        authorHomepageLabel.setText("Hjemmeside");

        authorHomepageTextField.setText("http://morten.laukvik.no");

        youTubeLabel.setText("YouTube");

        youTubeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/laukvik/trainer/swing/icons/youtube.png")));

        authorHomePageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/laukvik/trainer/swing/icons/tb_shop.png")));
        authorHomePageButton.setText("WWW");

        org.jdesktop.layout.GroupLayout authorPanelLayout = new org.jdesktop.layout.GroupLayout(authorPanel);
        authorPanel.setLayout(authorPanelLayout);
        authorPanelLayout.setHorizontalGroup(
            authorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(authorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(authorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(authorHomepageLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, authorNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, authorPanelLayout.createSequentialGroup()
                        .add(authorHomepageTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(authorHomePageButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(authorNameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, authorPanelLayout.createSequentialGroup()
                        .add(authorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(youTubeTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                            .add(youTubeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(youTubeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        authorPanelLayout.setVerticalGroup(
            authorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(authorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(authorNameLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(authorNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(28, 28, 28)
                .add(authorHomepageLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(authorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(authorHomePageButton)
                    .add(authorHomepageTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(28, 28, 28)
                .add(youTubeLabel)
                .add(5, 5, 5)
                .add(authorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(youTubeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(youTubeTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(256, 256, 256))
        );
        jTabbedPane1.addTab("Forfatter", authorPanel);

        musclesScrollPane.setBorder(null);
        musclesPanel.setLayout(new javax.swing.BoxLayout(musclesPanel, javax.swing.BoxLayout.Y_AXIS));

        exampleMuscleCheckbox1.setText("Legs");
        exampleMuscleCheckbox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        exampleMuscleCheckbox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        musclesPanel.add(exampleMuscleCheckbox1);

        exampleMuscleCheckbox2.setText("Thigh (front)");
        exampleMuscleCheckbox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        exampleMuscleCheckbox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        musclesPanel.add(exampleMuscleCheckbox2);

        musclesScrollPane.setViewportView(musclesPanel);

        jTabbedPane1.addTab("Muskler", musclesScrollPane);

        photosScrollPane.setBorder(null);
        examplePhotoPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        org.jdesktop.layout.GroupLayout examplePhotoPanel1Layout = new org.jdesktop.layout.GroupLayout(examplePhotoPanel1);
        examplePhotoPanel1.setLayout(examplePhotoPanel1Layout);
        examplePhotoPanel1Layout.setHorizontalGroup(
            examplePhotoPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        examplePhotoPanel1Layout.setVerticalGroup(
            examplePhotoPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        photosPanel.add(examplePhotoPanel1);

        photosScrollPane.setViewportView(photosPanel);

        jTabbedPane1.addTab("Bilder", photosScrollPane);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

    }// </editor-fold>                        
    
    
    // Variables declaration - do not modify                     
    javax.swing.JButton authorHomePageButton;
    javax.swing.JLabel authorHomepageLabel;
    javax.swing.JTextField authorHomepageTextField;
    javax.swing.JLabel authorNameLabel;
    javax.swing.JTextField authorNameTextField;
    javax.swing.JPanel authorPanel;
    javax.swing.JLabel descriptionLabel;
    javax.swing.JTextArea descriptionTextarea;
    javax.swing.JCheckBox exampleMuscleCheckbox1;
    javax.swing.JCheckBox exampleMuscleCheckbox2;
    javax.swing.JPanel examplePhotoPanel1;
    javax.swing.JPanel infoPanel;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTabbedPane jTabbedPane1;
    javax.swing.JComboBox languageComboBox;
    javax.swing.JLabel languageLabel;
    javax.swing.JPanel musclesPanel;
    javax.swing.JScrollPane musclesScrollPane;
    javax.swing.JLabel nameLabel;
    javax.swing.JTextField nameTextField;
    javax.swing.JButton overviewAuthorHomepageButton;
    javax.swing.JScrollPane overviewDescriptionScrollPane;
    javax.swing.JTextArea overviewDescriptionTextArea;
    javax.swing.JLabel overviewNameLabel;
    javax.swing.JPanel overviewPanel;
    DroppableIcon overviewStartIcon;
    javax.swing.JPanel overviewStartPanel;
    DroppableIcon overviewStopIcon;
    javax.swing.JPanel overviewStopPanel;
    javax.swing.JButton overviewYouTubeButton;
    javax.swing.JPanel photosPanel;
    javax.swing.JScrollPane photosScrollPane;
    javax.swing.JCheckBox visibleCheckbox;
    javax.swing.JButton youTubeButton;
    javax.swing.JLabel youTubeLabel;
    javax.swing.JTextField youTubeTextField;
    // End of variables declaration                   
    
}