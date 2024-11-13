package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import com.cantarino.souza.view.components.*;

public class DlgOrientacoes extends JDialog {

        JPanel panBackground;
        JPanel panLeft;
        JLabel lblImage;
        JLabel lblAction;
        JPanel panOptions;
        JButton btn1trimestre;
        JButton btn2trimestre;
        JButton btn3trimestre;
        JScrollPane scrArea;
        JTextPane txtContent;
        JScrollBar scrBar;

        StyledDocument doc;
        Style stlDefault;
        Style stlTitle;

        public DlgOrientacoes(JFrame parent, boolean modal) {
                super(parent, modal);
                initComponents();
        }

        private void initComponents() {
                setTitle("Orientações");
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(1920, 1080);
                setLocationRelativeTo(null);

                panBackground = new BackgroundPanel("/images/background.png");
                panBackground.setLayout(new GridBagLayout());
                setContentPane(panBackground);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 0.65;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.insets = new Insets(0, 0, 0, 10);
                panLeft = new JPanel();
                panLeft.setBackground(AppColors.TRANSPARENT);
                panLeft.setPreferredSize(new Dimension(0, 0));
                panLeft.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panBackground.add(panLeft, gbc);

                gbc.gridx = 1;
                gbc.weightx = 0.35;
                gbc.insets = new Insets(0, 0, 0, 0);
                lblImage = new JLabel(new ImageIcon(getClass().getResource("/images/orientations.png")));
                lblImage.setPreferredSize(new Dimension(0, 0));
                panBackground.add(lblImage, gbc);

                panLeft.setLayout(new GridBagLayout());

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.NORTH;
                gbc.insets = new Insets(0, 0, 10, 0);
                lblAction = new JLabel("Dicas e Orientações");
                lblAction.setFont(new Font("Arial", Font.BOLD, 64));
                lblAction.setForeground(AppColors.TITLE_BLUE);
                panLeft.add(lblAction, gbc);

                gbc.gridy = 1;
                panOptions = new JPanel(new GridLayout(1, 3, 10, 0));
                panOptions.setBackground(AppColors.TRANSPARENT);
                panOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panOptions.setPreferredSize(new Dimension(0, 75));
                panLeft.add(panOptions, gbc);

                btn1trimestre = new RoundedButton("1° Trimestre", 50);
                btn1trimestre.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn1trimestre.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn1trimestreActionPerformed(evt);
                        }
                });
                panOptions.add(btn1trimestre);

                btn2trimestre = new RoundedButton("2° Trimestre", 50);
                btn2trimestre.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn2trimestre.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn2trimestreActionPerformed(evt);
                        }
                });
                panOptions.add(btn2trimestre);

                btn3trimestre = new RoundedButton("3° Trimestre", 50);
                btn3trimestre.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn3trimestre.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn3trimestreActionPerformed(evt);
                        }
                });
                panOptions.add(btn3trimestre);

                gbc.gridy = 2;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.BOTH;
                txtContent = new JTextPane();
                txtContent.setBackground(AppColors.FIELD_PINK);
                txtContent.setBorder(BorderFactory.createEmptyBorder());
                scrArea = new JScrollPane(txtContent);
                scrArea.setBorder(null);
                panLeft.add(scrArea, gbc);

                scrBar = scrArea.getVerticalScrollBar();
                scrBar.setBackground(AppColors.BUTTON_PINK);
                scrBar.setForeground(AppColors.BUTTON_PINK);

                doc = txtContent.getStyledDocument();
                stlDefault = txtContent.addStyle("default", null);
                stlTitle = txtContent.addStyle("title", stlDefault);
                StyleConstants.setBold(stlTitle, true);
                StyleConstants.setFontSize(stlTitle, 18);

                btn1trimestreActionPerformed(null);

        }

        private void btn1trimestreActionPerformed(java.awt.event.ActionEvent evt) {
                txtContent.setText("");
                try {
                        doc.insertString(doc.getLength(), "Guia para o Primeiro Trimestre de Gravidez\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO primeiro trimestre é uma fase especial, cheia de mudanças no corpo e adaptações emocionais. \nConhecer o que esperar e saber como lidar com esses novos desafios pode ajudar a tornar essa fase mais tranquila e segura para você e seu bebê.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n1. Entendendo o Primeiro Trimestre\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n1.1 O Que Acontece no Primeiro Trimestre?\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO primeiro trimestre engloba as primeiras 12 semanas de gravidez, período em que o corpo passa por uma série de adaptações para abrigar e nutrir o bebê. \nNesse estágio, o bebê cresce rapidamente e desenvolve órgãos essenciais. \nEsse é um momento delicado, pois o risco de aborto espontâneo é mais elevado, tornando essencial adotar hábitos saudáveis desde o início.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n1.2 Sintomas Comuns\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nDurante o primeiro trimestre, você pode notar sintomas como:\n" +
                                                        "\nNáuseas e vômitos: Muitas mulheres experimentam náuseas, especialmente de manhã (enjoo matinal).\n"
                                                        +
                                                        "Cansaço excessivo: Seu corpo está gastando mais energia para sustentar o crescimento do bebê, então sentir-se cansada é natural.\n"
                                                        +
                                                        "Mudanças emocionais: Os hormônios em constante mudança podem causar oscilações de humor.\n"
                                                        +
                                                        "Aumento da frequência urinária: O útero em crescimento começa a pressionar a bexiga.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n2. Alimentação no Primeiro Trimestre\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n2.1 Nutrientes Essenciais\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nUma alimentação equilibrada é fundamental para o desenvolvimento do bebê e para a saúde da gestante. Alguns nutrientes são especialmente importantes:\n"
                                                        +
                                                        "- Ácido fólico: Ajuda na formação do tubo neural do bebê. Pode ser encontrado em vegetais verdes escuros, feijão, e suplementos recomendados pelo médico.\n"
                                                        +
                                                        "- Ferro: Essencial para evitar anemia e garantir o transporte adequado de oxigênio. Carnes magras e vegetais de folhas verdes são boas fontes.\n"
                                                        +
                                                        "- Cálcio: Importante para o desenvolvimento ósseo do bebê. Inclua laticínios, tofu e vegetais verdes na dieta.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n2.2 Alimentos a Evitar\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nAlguns alimentos podem representar riscos e devem ser evitados:\n" +
                                                        "- Carnes cruas ou malpassadas: Podem conter bactérias e parasitas prejudiciais.\n"
                                                        +
                                                        "- Peixes ricos em mercúrio: Evite peixes como atum e cação, pois o mercúrio pode prejudicar o desenvolvimento neural do bebê.\n"
                                                        +
                                                        "- Queijos e laticínios não pasteurizados: Podem conter a bactéria Listeria, que oferece riscos ao bebê.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n3. Cuidados com o Corpo\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n3.1 Atividade Física\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nA prática de exercícios leves pode ajudar a melhorar o humor, aumentar a energia e reduzir o risco de complicações. Algumas recomendações:\n"
                                                        +
                                                        "- Caminhadas: Uma caminhada leve é excelente para circulação e não sobrecarrega o corpo.\n"
                                                        +
                                                        "- Yoga para gestantes: Ajuda no alongamento e na flexibilidade, além de promover o relaxamento.\n"
                                                        +
                                                        "- Exercícios de respiração: A prática de respirações profundas auxilia na calma e melhora o fluxo de oxigênio para o bebê.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n3.2 Hidratação\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nManter-se hidratada é fundamental para reduzir o risco de infecções urinárias e ajudar na circulação sanguínea. Recomenda-se beber ao menos 2 litros de água por dia, ajustando de acordo com o nível de atividade física e o clima.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n3.3 Sono\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO corpo da gestante precisa de mais descanso, então dormir o suficiente é essencial. Tente adotar horários regulares e use travesseiros extras para apoiar a barriga e evitar desconfortos.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n4. Saúde Emocional e Apoio\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n4.1 Gerenciamento de Estresse\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nMudanças hormonais e o novo papel que a gestação traz podem causar ansiedade. Algumas estratégias para aliviar o estresse incluem:\n"
                                                        +
                                                        "- Meditação: Práticas de meditação guiada podem ajudar a acalmar a mente.\n"
                                                        +
                                                        "- Diálogo com o parceiro e a família: Conversar sobre as ansiedades e preocupações pode trazer apoio e alívio.\n"
                                                        +
                                                        "- Apoio psicológico: Profissionais especializados podem oferecer ajuda em momentos de maior dificuldade.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n4.2 Rede de Apoio\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nTer uma rede de apoio (família, amigos ou grupos de gestantes) pode tornar a jornada mais tranquila. Participe de grupos de grávidas online ou em sua cidade para trocar experiências e sentir-se acolhida.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n5. Primeiros Exames e Acompanhamento Médico\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n5.1 Consultas e Exames Importantes\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO acompanhamento médico é essencial para garantir uma gestação saudável. No primeiro trimestre, você será orientada a realizar exames importantes como:\n"
                                                        +
                                                        "- Ultrassonografia: Para verificar o desenvolvimento do bebê e estimar a idade gestacional.\n"
                                                        +
                                                        "- Exames de sangue e urina: Avaliam a presença de infecções, anemia e outras condições de saúde.\n"
                                                        +
                                                        "- Exames para doenças infecciosas: Verificam a presença de sífilis, HIV, hepatite B, entre outros.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n5.2 Quando Procurar Ajuda\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nProcure atendimento médico se apresentar sintomas como sangramentos, dores fortes, febre persistente ou outros desconfortos fora do comum. Esses sinais podem indicar complicações e requerem atenção.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6. Dicas Gerais para o Primeiro Trimestre\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n6.1 Use Roupas Confortáveis\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nConforme o corpo muda, roupas mais soltas podem oferecer maior conforto. Prefira tecidos leves e elásticos, especialmente na região da cintura.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6.2 Evite Produtos Tóxicos\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nCertos produtos químicos encontrados em tintas, esmaltes e produtos de limpeza podem ser prejudiciais durante a gestação. Evite o uso de produtos tóxicos ou procure alternativas mais seguras.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6.3 Planeje um Calendário\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nOrganize suas atividades para evitar sobrecargas. Planeje suas consultas e mantenha um registro de sintomas e mudanças no corpo.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\nConclusão\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO primeiro trimestre de gravidez é uma fase de grandes transformações físicas e emocionais. Adotar hábitos saudáveis, manter um bom acompanhamento médico e cuidar da saúde emocional são passos fundamentais para uma gestação tranquila e saudável. Com estas orientações, esperamos que você se sinta mais confiante e preparada para as próximas etapas dessa linda jornada.\n",
                                        stlDefault);

                } catch (BadLocationException e) {
                        e.printStackTrace();
                }
                txtContent.setCaretPosition(0);
        }

        private void btn2trimestreActionPerformed(java.awt.event.ActionEvent evt) {
                txtContent.setText("");
                try {
                        doc.insertString(doc.getLength(), "Guia para o Segundo Trimestre de Gravidez\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO segundo trimestre, que vai da 13ª à 26ª semana, é geralmente conhecido como o período mais tranquilo da gravidez. Durante essa fase, muitos dos desconfortos do primeiro trimestre diminuem, e a gestante começa a notar mudanças mais visíveis no corpo e no desenvolvimento do bebê.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n1. O Que Esperar no Segundo Trimestre\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n1.1 Desenvolvimento do Bebê\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nDurante o segundo trimestre, o bebê continua a crescer rapidamente. Alguns marcos importantes incluem:\n"
                                                        +
                                                        "- Desenvolvimento dos sentidos: O bebê começa a reagir a sons externos e luz.\n"
                                                        +
                                                        "- Movimentos perceptíveis: Muitas gestantes começam a sentir os movimentos do bebê, conhecidos como 'chutes', entre a 18ª e a 22ª semana.\n"
                                                        +
                                                        "- Crescimento acelerado: O bebê começa a ganhar mais peso, e seu esqueleto vai se fortalecendo.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n1.2 Mudanças no Corpo da Gestante\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nNo segundo trimestre, o corpo da gestante continua se adaptando:\n" +
                                                        "- Barriga em crescimento: O útero cresce, tornando a gravidez mais visível.\n"
                                                        +
                                                        "- Desconfortos ocasionais: Pode surgir dor nas costas ou no abdômen devido ao peso extra.\n"
                                                        +
                                                        "- Melhora nos sintomas iniciais: Náuseas e fadiga costumam reduzir, proporcionando maior energia para o dia a dia.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n2. Alimentação e Suplementação\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n2.1 Nutrientes Importantes\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nA alimentação continua desempenhando um papel essencial. Nutrientes específicos para este trimestre incluem:\n"
                                                        +
                                                        "- Cálcio e vitamina D: Importantes para o desenvolvimento ósseo do bebê. Laticínios, ovos e exposição ao sol são boas fontes.\n"
                                                        +
                                                        "- Ácidos graxos ômega-3: Essenciais para o desenvolvimento cerebral do bebê. Encontrados em peixes como salmão e sementes de linhaça.\n"
                                                        +
                                                        "- Fibra: Ajuda a prevenir constipação, um problema comum. Inclua frutas, legumes e cereais integrais.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n2.2 Controle do Peso\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nEmbora seja natural ganhar peso durante a gravidez, o aumento deve ser gradual. Manter uma alimentação equilibrada ajuda a controlar o ganho de peso e contribui para a saúde da mãe e do bebê.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n3. Exercícios Físicos\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n3.1 Atividades Recomendadas\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO exercício físico pode ajudar a reduzir o estresse, melhorar o humor e preparar o corpo para o parto. Opções seguras incluem:\n"
                                                        +
                                                        "- Caminhadas e hidroginástica: Atividades de baixo impacto que ajudam na circulação.\n"
                                                        +
                                                        "- Yoga para gestantes: Promove o relaxamento e a flexibilidade.\n"
                                                        +
                                                        "- Exercícios de fortalecimento: Com orientação médica, exercícios leves com peso ajudam a fortalecer a musculatura, preparando para o aumento de peso e o parto.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n3.2 Cuidados ao Praticar Exercícios\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nEvite exercícios intensos ou de alto impacto e sempre ouça os sinais do seu corpo. É importante beber bastante água e fazer pausas sempre que necessário.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n4. Saúde Emocional e Preparação para o Parto\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n4.1 Reduzindo a Ansiedade\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nDurante o segundo trimestre, a ideia do parto e da maternidade começa a ganhar mais forma. Algumas estratégias podem ajudar a reduzir a ansiedade:\n"
                                                        +
                                                        "- Educação sobre o parto: Fazer cursos ou assistir vídeos pode ajudar a desmistificar o processo do parto.\n"
                                                        +
                                                        "- Planejamento de apoio: Conversar com o parceiro e a família sobre os planos para a chegada do bebê ajuda a criar um ambiente de segurança.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n4.2 Preparação Mental\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nTécnicas de respiração e meditação são úteis para fortalecer a mente e trazer calma. Práticas diárias podem ajudar a criar uma rotina de autocuidado que será útil até o pós-parto.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n5. Acompanhamento Médico e Exames\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n5.1 Exames Importantes\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nNo segundo trimestre, o acompanhamento médico continua essencial. Alguns exames comuns incluem:\n"
                                                        +
                                                        "- Ultrassom morfológico: Realizado entre a 18ª e a 22ª semana, verifica o desenvolvimento e a formação dos órgãos do bebê.\n"
                                                        +
                                                        "- Exames de glicose: Avaliam o risco de diabetes gestacional.\n"
                                                        +
                                                        "- Exames de sangue e urina: Continuam para monitorar a saúde geral da gestante.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n5.2 Sinais de Alerta\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nProcure ajuda médica caso observe sangramento, dor intensa, febre ou inchaço nas mãos e no rosto. Esses sinais podem indicar complicações e precisam de atenção.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6. Dicas Gerais para o Segundo Trimestre\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n6.1 Vestuário Confortável\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nCom o aumento da barriga, roupas confortáveis e adaptadas para gestantes ajudam a evitar desconforto. Prefira tecidos elásticos e leves.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6.2 Preparação do Enxoval\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO segundo trimestre é um bom momento para começar a planejar o enxoval do bebê e escolher itens necessários para o nascimento. Planeje com antecedência para evitar pressa no final da gestação.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6.3 Aproveite o Momento\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nAproveite este período mais tranquilo para registrar memórias, como fotos da barriga e escrever sobre os sentimentos em relação ao bebê. Esses registros podem ser muito especiais no futuro.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\nConclusão\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO segundo trimestre é uma fase de preparação e conexão com o bebê. Com hábitos saudáveis, uma boa alimentação, apoio emocional e acompanhamento médico regular, você estará se preparando tanto fisicamente quanto mentalmente para o parto e para a chegada do bebê. Aproveite este momento especial e cuide bem de você mesma.\n",
                                        stlDefault);

                } catch (BadLocationException e) {
                        e.printStackTrace();
                }
                txtContent.setCaretPosition(0);
        }

        private void btn3trimestreActionPerformed(java.awt.event.ActionEvent evt) {
                txtContent.setText("");
                try {
                        doc.insertString(doc.getLength(), "Guia para o Terceiro Trimestre de Gravidez\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO terceiro trimestre, que vai da 27ª semana até o parto, é um período de grande expectativa e preparação para o nascimento. Nessa fase, o bebê continua crescendo e se preparando para o mundo exterior, e o corpo da mãe passa por transformações para o momento do parto.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n1. Desenvolvimento do Bebê no Terceiro Trimestre\n",
                                        stlTitle);

                        doc.insertString(doc.getLength(), "\n1.1 Crescimento e Desenvolvimento\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nDurante o terceiro trimestre, o bebê ganha peso rapidamente e os órgãos continuam a amadurecer. Alguns aspectos importantes incluem:\n"
                                                        +
                                                        "- Preparação pulmonar: Os pulmões do bebê começam a produzir surfactante, uma substância que o ajudará a respirar após o nascimento.\n"
                                                        +
                                                        "- Reflexos: O bebê começa a desenvolver reflexos básicos, como o de sucção, essencial para a amamentação.\n"
                                                        +
                                                        "- Posição de parto: Nas últimas semanas, o bebê geralmente se posiciona de cabeça para baixo, preparando-se para o nascimento.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n1.2 Movimentos e Conexão\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nNo terceiro trimestre, os movimentos do bebê podem se tornar mais perceptíveis e, em alguns casos, mais intensos. Isso é um sinal de saúde e vitalidade.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n2. Mudanças no Corpo da Gestante\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n2.1 Desconfortos Comuns\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO corpo da gestante passa por mudanças significativas para suportar o peso do bebê:\n"
                                                        +
                                                        "- Inchaço: Com o aumento do volume sanguíneo e a retenção de líquidos, é comum sentir inchaço nas pernas e pés.\n"
                                                        +
                                                        "- Falta de ar: À medida que o útero cresce, ele pressiona o diafragma, dificultando a respiração.\n"
                                                        +
                                                        "- Azia e indigestão: A pressão do útero no estômago pode causar desconfortos digestivos.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n2.2 Preparação para o Parto\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nNo terceiro trimestre, o corpo começa a se preparar para o trabalho de parto. Algumas mulheres sentem contrações de treinamento, conhecidas como contrações de Braxton Hicks, que preparam o útero para o parto.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n3. Alimentação e Suplementação no Terceiro Trimestre\n",
                                        stlTitle);

                        doc.insertString(doc.getLength(), "\n3.1 Nutrição para o Parto\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nDurante o terceiro trimestre, manter uma alimentação balanceada é crucial. Alguns nutrientes específicos incluem:\n"
                                                        +
                                                        "- Ferro: Importante para prevenir anemia, especialmente com o aumento do volume sanguíneo. Fontes incluem carnes magras, feijões e folhas verdes.\n"
                                                        +
                                                        "- Proteínas: Essenciais para o crescimento do bebê. Inclua ovos, peixes e leguminosas.\n"
                                                        +
                                                        "- Fibras: Ajudam a aliviar a constipação, um sintoma comum nesse estágio.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n3.2 Hidratação\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nBeber bastante água ajuda a combater o inchaço e a prevenir infecções urinárias, comuns durante o final da gravidez.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n4. Exercícios Físicos no Terceiro Trimestre\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n4.1 Atividades Recomendadas\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nExercícios leves podem ajudar a reduzir desconfortos e preparar o corpo para o parto. Recomenda-se:\n"
                                                        +
                                                        "- Caminhadas leves: Ajudam na circulação e aliviam o inchaço.\n"
                                                        +
                                                        "- Exercícios de respiração e alongamento: Preparam o corpo para o parto e ajudam a relaxar.\n"
                                                        +
                                                        "- Yoga e Pilates para gestantes: Ajudam a fortalecer o assoalho pélvico e melhorar a postura.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n4.2 Precauções Importantes\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nEvite atividades que exijam muito esforço ou que envolvam risco de quedas. Sempre consulte seu médico antes de iniciar ou continuar qualquer rotina de exercícios.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n5. Preparação para o Parto e Pós-Parto\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n5.1 Planos de Parto\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO terceiro trimestre é um bom momento para discutir e preparar seu plano de parto. Considere:\n"
                                                        +
                                                        "- Local de parto: Decida se prefere hospital, casa de parto ou parto domiciliar (se disponível e seguro).\n"
                                                        +
                                                        "- Opções de alívio da dor: Conheça as opções e converse com seu médico sobre o que é mais adequado para você.\n"
                                                        +
                                                        "- Contato com a equipe médica: Esteja em comunicação com seu obstetra para esclarecer dúvidas e realizar os preparativos necessários.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n5.2 Preparação para o Pós-Parto\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nPlaneje com antecedência itens e apoios para o pós-parto, incluindo:\n" +
                                                        "- Itens de higiene e conforto para você e o bebê.\n" +
                                                        "- Apoio familiar: Organize ajuda para as primeiras semanas após o parto.\n"
                                                        +
                                                        "- Visitas e descanso: Combine com familiares e amigos sobre horários para visitas e período de adaptação.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6. Exames e Consultas no Terceiro Trimestre\n", stlTitle);

                        doc.insertString(doc.getLength(), "\n6.1 Exames Comuns\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nDurante o terceiro trimestre, algumas consultas e exames essenciais incluem:\n"
                                                        +
                                                        "- Monitoramento da posição do bebê: Para avaliar a posição e, se necessário, considerar orientações para favorecer a posição de parto.\n"
                                                        +
                                                        "- Exames de sangue: Avaliam possíveis anemias ou outras condições que precisam de acompanhamento.\n"
                                                        +
                                                        "- Monitoramento da pressão arterial: Essencial para detectar qualquer sinal de pré-eclâmpsia.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\n6.2 Sinais de Alerta\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nEntre em contato com o médico se notar qualquer sinal de alerta, como sangramentos, dor intensa, contrações regulares antes das 37 semanas ou diminuição dos movimentos do bebê.\n",
                                        stlDefault);

                        doc.insertString(doc.getLength(), "\nConclusão\n", stlTitle);
                        doc.insertString(doc.getLength(),
                                        "\nO terceiro trimestre é uma fase de grande expectativa e preparação. Ao manter uma rotina saudável, realizar os exames e preparar-se mental e fisicamente para o parto, você estará pronta para acolher o bebê. Lembre-se de que o apoio familiar e o acompanhamento médico são fundamentais para uma gestação segura e saudável até o momento do nascimento.\n",
                                        stlDefault);

                } catch (BadLocationException e) {
                        e.printStackTrace();
                }
                txtContent.setCaretPosition(0);
        }

}
