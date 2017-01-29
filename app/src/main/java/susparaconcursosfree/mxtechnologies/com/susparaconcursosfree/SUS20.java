package susparaconcursosfree.mxtechnologies.com.susparaconcursosfree;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

/**
 * Created by Moisés on 10/02/2016.
 */
public class SUS20 extends Fragment {
    private TextView questaoTextView;
    private TextView textView;
    private RadioButton a;
    private RadioButton b;
    private RadioButton c;
    private RadioButton d;
    private RadioButton e;
    private RadioGroup rg;
    private int opcao;
    private int tentativas;
    private int alternativa;
    int[] nquestion;
    int nquestions;
    int m;
    InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content_sus,container,false);

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-4218805607147921/8079463292");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();

        nquestions = 50;
        m=0;
        tentativas=0;
        textView = (TextView) view.findViewById(R.id.textView);
        questaoTextView = (TextView) view.findViewById(R.id.questaoTextView);
        rg = (RadioGroup)view.findViewById(R.id.rgopcoes);
        a = (RadioButton)view.findViewById(R.id.a);
        b = (RadioButton)view.findViewById(R.id.b);
        c = (RadioButton)view.findViewById(R.id.c);
        d = (RadioButton)view.findViewById(R.id.d);
        e = (RadioButton)view.findViewById(R.id.e);
        nquestion = new int[10];
        int tquestions [];
        int aux;
        Random random  = new Random();
        tquestions = new int[nquestions];
        for(int b=0;b<nquestions;b++){
            tquestions[b]=b+1;
        }
        for (int n=0;n<10;n++){
            do{
                aux=random.nextInt(nquestions);
                nquestion[n]=tquestions[aux];
            }while(tquestions[aux]==0);
            tquestions[aux]=0;
        }

        alternativa=update(nquestion[m]);
        textView.setText((m + 1) + " de "+nquestion.length);

        Button button1 = (Button)view.findViewById(R.id.button1);
        button1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        opcao = rg.getCheckedRadioButtonId();
                        alternativa = update(nquestion[m]);
                        textView.setText((m + 1) + " de "+nquestion.length);
                        if (opcao != alternativa) {
                            tentativas = tentativas + 1;
                            AlertDialog.Builder mensagem = new AlertDialog.Builder(getActivity());
                            mensagem.setTitle("Atenção!");
                            mensagem.setMessage("Alternativa incorreta");
                            mensagem.setNeutralButton("OK", null);
                            mensagem.show();
                        }

                        if (opcao == alternativa) {
                            if (m == (nquestion.length-1)) {
                                tentativas = tentativas + 1;
                                AlertDialog.Builder mensagem1 = new AlertDialog.Builder(getActivity());
                                mensagem1.setTitle("   ESTATÍSTICAS");
                                mensagem1.setMessage(tentativas + " tentativas para responder " + nquestion.length + " questões");
                                mensagem1.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (mInterstitialAd.isLoaded()) {
                                            mInterstitialAd.show();
                                        }
                                    }
                                });
                                mensagem1.show();
                            }

                            if (m < (nquestion.length-1)) {
                                tentativas = tentativas + 1;
                                AlertDialog.Builder mensagem = new AlertDialog.Builder(getActivity());
                                mensagem.setTitle("Parabéns!");
                                mensagem.setMessage("Alternativa correta");
                                mensagem.setNeutralButton("OK", null);
                                mensagem.show();
                                m = m + 1;
                                alternativa = update(nquestion[m]);
                                textView.setText((m + 1) + " de " + nquestion.length);
                            }
                        }
                    }
                });
        Button button2 = (Button)view.findViewById(R.id.button2);
        button2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick (View v){
                        if(m>0) {
                            m = m - 1;
                            alternativa = update(nquestion[m]);
                            textView.setText((m + 1) + " de "+nquestion.length);
                        }else{
                            Toast.makeText(getActivity(), "Início do Teste", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return view;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private int update(int question) {
        int alt = -1;
        if (question == 0) {
            questaoTextView.setText("Na prestação de serviços de assistência à saúde, serão observados os princípios éticos e as normas expedidas pelo __________________ quanto às condições para seu funcionamento. ");
            a.setText("Conselho Nacional de Saúde");
            b.setText("Órgão de direção do SUS");
            c.setText("Ministério da Saúde");
            d.setText("Ministro da Saúde");
            e.setText("Congresso Nacional");
            alt = R.id.a;
        }
        if (question == 1) {
            questaoTextView.setText("A Lei 8.080/90 dispõe textualmente que “é vedada a participação direta ou indireta de empresas ou de capitais estrangeiros na assistência à saúde, salvo através de doações de organismos internacionais vinculados aos(à)_________________, de entidades de cooperação técnica e de financiamentos e empréstimos”. ");
            a.setText("Empresas Internacionais que atuam na área de Seguro Saúde");
            b.setText("Empresas Internacionais que atuam na área de Seguro Saúde");
            c.setText("Bancos Internacionais");
            d.setText("Bancos Nacionais");
            e.setText("Organização das Nações Unidas (ONU)");
            alt = R.id.e;
        }
        if (question == 2) {
            questaoTextView.setText("Quando as suas disponibilidades forem insuficientes para garantir a cobertura assistencial à população de uma determinada área o Sistema Único de Saúde (SUS) ________________ recorrer aos serviços ofertados pela iniciativa privada. ");
            a.setText("com autorização do Congresso Nacional, deverá");
            b.setText("está proibido de");
            c.setText("deverá obrigatoriamente");
            d.setText("poderá");
            e.setText("não poderá");
            alt = R.id.d;
        }
        if (question == 3) {
            questaoTextView.setText("Em relação as Normas Operacionais Básicas (NOB) e Norma Operacional de Assistência à Saúde (NOAS), assinale a alternativa correta.");
            a.setText("As NOB têm como objetivo principal regulamentar o Sistema Único de Saúde.");
            b.setText("A NOB 96 é um marco na questão da centralização das ações e serviços de saúde, no qual o nível estadual consegue maior autonomia para prestação de serviços básicos de saúde.");
            c.setText("A operacionalização da NOB-96 se dá preferencialmente no nível federal, pois a análise da situação de saúde só pode ser realizada de forma equitativa mediante a tomada de decisão do governo federal.");
            d.setText("A operacionalização da NOB-96 se dá preferencialmente no nível federal, pois a análise da situação de saúde só pode ser realizada de forma equitativa mediante a tomada de decisão do governo federal.");
            e.setText("A NOAS-SUS atualiza a regulamentação da assistência, considerando os avanços já obtidos e enfocando os desafios a serem superados no processo permanente de consolidação e aprimoramento do Sistema Único de Saúde, e cria em 2001 o Piso da Atenção Básica (PAB).");
            alt = R.id.d;
        }
        if (question == 4) {
            questaoTextView.setText("De acordo com a Lei orgânica de saúde 8080/90, assinale a alternativa correta.");
            a.setText("Esta lei dispõe sobre a participação da comunidade na gestão do Sistema Único de Saúde (SUS) e sobre as transferências intergovernamentais de recursos financeiros na área da saúde.");
            b.setText("A lei 8080/90 cria o Piso da Atenção Básica (PAB), que consiste em recursos financeiros destinados a investimentos de procedimentos e ações de assistência básica, tipicamente municipal. Onde é distribuído per capta um valor de 10 a 18 reais por habitante do município.");
            c.setText("De acordo com a Lei 8080/90 a iniciativa privada poderá participar do Sistema Único de Saúde (SUS), em caráter complementar.");
            d.setText("A lei 8080/90 altera os artigos. 34, 35, 156, 160, 167 e 198 da Constituição Federal e acrescenta artigo ao ato das Disposições Constitucionais Transitórias, para assegurar os recursos mínimos para o financiamento das ações e serviços públicos de saúde.");
            e.setText("A Emenda constitucional número 29 foi proposta pela constituição federal de 1988, regulamentada pela lei 8080/90, porém só entrou efetivamente em vigor no ano de 2006.");
            alt = R.id.c;
        }
        if (question == 5) {
            questaoTextView.setText("A Lei que dispõe sobre a participação da comunidade na gestão do Sistema Único de Saúde (SUS) é");
            a.setText("a Lei n.º 8.142, de 28 de dezembro de 1990");
            b.setText("a Lei n.º 10.406, de 10 de janeiro de 2002");
            c.setText("a Lei n.º 5.869, de 11 de janeiro de 1973");
            d.setText("a Lei n.º 8.080, de 19 de dezembro de 1990");
            e.setText("o Decreto-Lei nº 2.848, de 7 de dezembro de 1940");
            alt = R.id.a;
        }
        if (question == 6) {
            questaoTextView.setText("Acerca da Ordem Social, de acordo com a Constituição Federal brasileira, de 05 de outubro de 1988, é correto afirmar que");
            a.setText("a seguridade social compreende um conjunto integrado de ações de iniciativa exclusiva dos Poderes Públicos.");
            b.setText("a seguridade social será financiada por toda a sociedade, de forma direta e indireta, nos termos da lei, mediante recursos provenientes dos orçamentos da União, dos Estados, do Distrito Federal e dos Municípios e de contribuições sociais.");
            c.setText("a saúde é direito de todos e dever de cada cidadão.");
            d.setText("não são de relevância pública as ações e serviços de saúde.");
            e.setText("a assistência à saúde é exclusiva do Poder Público.");
            alt = R.id.b;
        }
        if (question == 7) {
            questaoTextView.setText("A Constituição Federal brasileira, de 05 de outubro de 1988, declara que a saúde é direito de todos e dever");
            a.setText("do particular");
            b.setText("da escola");
            c.setText("dos planos de saúde");
            d.setText("do Estado");
            e.setText("das empresas");
            alt = R.id.d;
        }
        if (question == 8) {
            questaoTextView.setText("Conforme dispõe o art. 28 da Lei nº 8.080, de 19 de setembro de 1990, os cargos e funções de chefia, direção e assessoramento, no âmbito do Sistema Único de Saúde (SUS), só poderão ser exercidos");
            a.setText("em regime de tempo integral.");
            b.setText("em regime de tempo parcial de 04 (quatro) horas.");
            c.setText("em escala de 24 (vinte e quatro) horas por 48 (quarenta e oito) horas.");
            d.setText("sem ter horário determinado, por tratar-se de cargo de confiança.");
            e.setText("em regime de tempo parcial de 06 (seis) horas.");
            alt = R.id.a;
        }
        if (question == 9) {
            questaoTextView.setText("De acordo com o Decreto Federal nº 7.508, de 28 de junho de 2011, o acesso universal e igualitário à assistência farmacêutica pressupõe");
            a.setText("não estar o usuário assistido por ações e serviços de saúde do SUS.");
            b.setText("ter o medicamento sido prescrito por qualquer pessoa.");
            c.setText("estar a prescrição em conformidade com a RENAME e os Protocolos Clínicos e Diretrizes Terapêuticas ou com a relação específica complementar estadual, distrital ou municipal de medicamentos.");
            d.setText("não ter a dispensação ocorrido em unidades indicadas pela direção do SUS.");
            e.setText("estar o usuário assistido por ações e serviços de saúde particular.");
            alt = R.id.c;
        }
        if (question == 10) {
            questaoTextView.setText("Assinale a alternativa correta.");
            a.setText("O dever do Estado de garantir a saúde consiste na formulação e execução de políticas econômicas e sociais que visem à redução de riscos de doenças e de outros agravos e no estabelecimento de condições que assegurem acesso universal e igualitário às ações e aos serviços para a sua promoção, proteção e recuperação.");
            b.setText("O dever do Estado em garantir a saúde exclui o das pessoas, da família, das empresas e da sociedade.");
            c.setText("O Sistema Único de Saúde é o conjunto de ações e serviços de saúde, prestados por órgãos e instituições públicas federal, estadual e municipal, exclusivamente da Administração Direta.");
            d.setText("A iniciativa privada participa concorrentemente do Sistema Único de Saúde.");
            e.setText("A saúde garantida pelo Sistema Único é exclusivamente física.");
            alt = R.id.a;
        }
        if (question == 11) {
            questaoTextView.setText("Assinale a alternativa correta.");
            a.setText("Universalidade de acesso aos serviços de saúde, nos primeiros níveis de assistência, é um dos princípios do Sistema Único de Saúde.");
            b.setText("Adireção do Sistema Único de Saúde (SUS) é dividida e descentralizada, sendo exercida a direção em cada esfera de governo.");
            c.setText("Os municípios não poderão constituir consórcios para desenvolver em conjunto as ações e os serviços de saúde que lhe correspondam.");
            d.setText("No nível municipal, o Sistema Único de Saúde não poderá organizar-se em distritos, de forma a integrar e articular recursos, técnicas e práticas voltadas para a cobertura total das ações de saúde.");
            e.setText("É princípio do Sistema Único de Saúde a organização dos serviços públicos de modo a evitar a duplicidade de meios para fins idênticos.");
            alt = R.id.e;
        }
        if (question == 12) {
            questaoTextView.setText("Assinale a alternativa correta.");
            a.setText("A Conferência de Saúde se reunirá a cada ano para avaliar a situação de saúde e propor diretrizes para formulação da política de saúde nos níveis correspondentes.");
            b.setText("O Sistema Único de Saúde contará, em cada esfera de governo, com a Conferência de Saúde e com o Conselho de Saúde, como instâncias colegiadas.");
            c.setText("Os recursos do Fundo Nacional de Saúde não serão alocados como despesas de custeio e de capital do Ministério da Saúde, seus órgãos e entidades, da administração direta e indireta");
            d.setText("Para receberem os recursos do Governo Federal, os municípios e os Estados não precisam ter plano de saúde.");
            e.setText("O Conselho Nacional de Secretários de Saúde e o Conselho Nacional de Secretarias Municipais de Saúde não terão representação no Conselho Nacional de Saúde.");
            alt = R.id.b;
        }
        if (question == 13) {
            questaoTextView.setText("Assinale a alternativa correta.");
            a.setText("Não é princípio do Sistema Único de Saúde a conjugação dos recursos financeiros, tecnológicos, materiais e humanos da União, dos Estados e do Distrito Federal na prestação de serviços de assistência à saúde da população.");
            b.setText("Não compete ao Sistema Único de Saúde controlar e fiscalizar procedimentos e substâncias de interesse para a saúde.");
            c.setText("Asaúde é direito de todos e dever do Estado, garantido mediante políticas sociais e econômicas que visem à redução do risco de doença e de outros agravos e ao acesso universal e igualitário às ações e serviços para sua promoção, proteção e recuperação.");
            d.setText("Somente a União e os Estados têm obrigação legal de aplicar valor mínimo de recursos na saúde.");
            e.setText("Os gestores locais do Sistema Único de Saúde poderão admitir agentes comunitários de saúde e agentes de combate às endemias por meio de contratação direta, sem teste seletivo ou concurso público, de acordo com a natureza e complexidade de suas atribuições e requisitos específicos para sua atuação.");
            alt = R.id.c;
        }
        if (question == 14) {
            questaoTextView.setText("Assinale a alternativa correta.");
            a.setText("No Brasil colônia, existia um sistema de saúde estruturado e a população procurava os médicos, recorrendo aos curandeiros somente por crendice.");
            b.setText("Mesmo com a chegada da Família Real Portuguesa ao Brasil, em 1808, o sistema de saúde pública no Brasil não mudou.");
            c.setText("Até 1900, não havia no Brasil faculdade de medicina.");
            d.setText("Em 1850, é criada a Junta Central de Higiene Pública, com o objetivo de coordenar as Juntas Municipais e, especialmente, atuar no combate à febre amarela. Esta junta também passou a coordenar as atividades de polícia sanitária, vacinação contra varíola, fiscalização do exercício da medicina e a Inspetoria de Saúde dos Portos.");
            e.setText("Mesmo com a evolução da saúde pública, no final do século XVIII, a atividade dos curandeiros era respeitada e permitida.");
            alt = R.id.d;
        }
        if (question == 15) {
            questaoTextView.setText("A Constituição Federal de 1988 estabeleceu de forma relevante uma seção sobre a saúde. Qual das alternativas a seguir faz parte dessa seção?");
            a.setText("A ordem social tem como base o primado do trabalho, e como objetivo o bem-estar e a justiça sociais.");
            b.setText("A União, os Estados, o Distrito Federal e os Municípios organizarão em regime de colaboração seus sistemas de saúde.");
            c.setText("Asaúde, direito de todos e dever do Estado e da família, será promovida e incentivada com a colaboração da sociedade, visando ao pleno desenvolvimento da pessoa, seu preparo para o exercício da cidadania e sua qualificação para o trabalho.");
            d.setText("São de relevância pública as ações e serviços de saúde, cabendo ao Poder Público dispor, nos termos da lei, sobre sua regulamentação, fiscalização e controle, devendo sua execução ser feita diretamente ou através de terceiros e, também, por pessoa física ou jurídica de direito privado.");
            e.setText("Os programas suplementares de alimentação e assistência a saúde serão financiados com recursos provenientes de contribuições sociais e outros recursos orçamentários.");
            alt = R.id.d;
        }
        if (question == 16) {
            questaoTextView.setText("Compete ao Poder Público, nos termos da lei, organizar a seguridade social. Qual item NÃO se enquadra nessa competência?");
            a.setText("Uniformidade e equivalência dos benefícios e serviços às populações urbanas e rurais.");
            b.setText("Equidade na forma de participação no custeio.");
            c.setText("Habilitação e reabilitação das pessoas portadoras de deficiência e a promoção de sua integração à vida comunitária.");
            d.setText("Caráter democrático e descentralizado da administração, mediante gestão quadripartite, com participação dos trabalhadores, dos empregadores, dos aposentados e do Governo nos órgãos colegiados.");
            e.setText("Universalidade da cobertura e do atendimento.");
            alt = R.id.c;
        }
        if (question == 17) {
            questaoTextView.setText("Compete ao SUS prestar assistência às pessoas, por intermédio de ações de promoção, proteção e recuperação da saúde, com a realização integrada das ações assistenciais e das atividades preventivas. Qual das alternativas a seguir NÃO se enquadra nessas ações?");
            a.setText("Realizar ações de vigilância sanitária e epidemiológica, bem como as de saúde do trabalhador.");
            b.setText("Realizar proteção à maternidade, especialmente à gestante.");
            c.setText("Ordenar a formação de recursos humanos na área de saúde.");
            d.setText("Participar da formulação da política e da execução das ações de saneamento básico.");
            e.setText("Fiscalizar e inspecionar alimentos, compreendido o controle de seu teor nutricional, bem como bebidas e águas para consumo humano.");
            alt = R.id.b;
        }
        if (question == 18) {
            questaoTextView.setText("O que é a Comissão Intergestores Tripartites do SUS?");
            a.setText("Instância de articulação e pactuação na esfera federal que atua na direção nacional do SUS, integrada por gestores do SUS das três esferas de governo.");
            b.setText("Comissão de gestores municipais, estaduais e federais que se encarregam dos planos estaduais, regionais e de regionalização das ações e serviços propostos pelos Colegiados de Gestão Regional.");
            c.setText("Um conjunto integrado de ações de iniciativa dos poderes públicos e da sociedade destinada a assegurar os direitos relativos à saúde, à previdência e à assistência social.");
            d.setText("Gestão compartilhada nos âmbitos federal e estadual, com direção única em cada esfera de governo.");
            e.setText("Espaços estaduais de articulação e pactuação  política que objetivam orientar, regulamentar e avaliar os aspectos operacionais do processo de descentralização das ações de saúde.");
            alt = R.id.a;
        }
        if (question == 19) {
            questaoTextView.setText("A Assistência Farmacêutica faz parte das políticas e dos programas de saúde do SUS. Assinale a alternativa que trata dos princípios dessa assistência.");
            a.setText("Política pública norteadora para a formulação de políticas setoriais, entre as quais destacam-se as políticas de medicamentos, não garantindo a intersetorialidade inerente ao sistema de saúde do país (SUS) e cuja implantação envolve o setor público de atenção à saúde.");
            b.setText("Controle do avanço científico e tecnológico em relação à produção de medicamentos.");
            c.setText("Manutenção de serviços de assistência farmacêutica na rede privada de saúde, nos diferentes níveis de atenção, considerando a necessária articulação e a observância das prioridades regionais definidas nas instâncias gestoras do SUS.");
            d.setText("Parte integrante da Política Nacional de Saúde, envolvendo um conjunto de ações voltadas à promoção, proteção e recuperação da saúde e garantindo os princípios da universalidade, integralidade e equidade.");
            e.setText("Política de capacitação e formação de profissionais na área farmacêutica, visando auxiliar a divulgação do uso correto dos medicamentos em atenção à saúde da família.");
            alt = R.id.b;
        }
        if (question == 20) {
            questaoTextView.setText("De acordo com o Decreto nº 7.508 de 28/06/2011, considera-se Região de Saúde");
            a.setText("o espaço geográfico contínuo constituído por agrupamentos de Municípios limítrofes, delimitado a partir de identidades culturais, econômicas e sociais e de redes de comunicação e infraestrutura de transportes compartilhados, com a finalidade de integrar a organização, o planejamento e a execução de ações e serviços de saúde.");
            b.setText("o espaço geográfico contínuo constituído por agrupamentos de Municípios limítrofes, delimitado a partir de identidades culturais, econômicas e sociais e de redes de comunicação e infraestrutura de transportes compartilhados, com a finalidade de interagir entre os Estados, Municípios e a União.");
            c.setText("todo o território Nacional, sem delimitação de identidades culturais, econômicas e sociais e de redes de comunicação e infraestrutura de transportes compartilhados.");
            d.setText("o espaço geográfico contínuo constituído por agrupamentos de Municípios limítrofes, delimitado a partir de identidades culturais, econômicas e sociais e de redes de comunicação e infraestrutura de transportes compartilhados, com a finalidade de integrar a organização, o planejamento e a execução de ações e serviços administrativos.");
            e.setText("o espaço geográfico contínuo constituído por agrupamentos de Municípios limítrofes, sem delimitação de identidades culturais, econômicas e sociais e de redes de comunicação e infraestrutura de transportes compartilhados, com a finalidade de integrar a organização, o planejamento e a execução de ações e serviços financeiros.");
            alt = R.id.a;
        }
        if (question == 22) {
            questaoTextView.setText("A direção do Sistema Único de Saúde (SUS) é única, sendo exercida em cada esfera de governo pelos seguintes órgãos:");
            a.setText("no âmbito da União, pelo Ministério da Previdência, no âmbito dos Estados e do Distrito Federal, pela respectiva Secretaria de Saúde ou órgão equivalente e, no âmbito dos Municípios, pela respectiva Secretaria de Saúde ou órgão equivalente.");
            b.setText("o âmbito da União, pelo Ministério da Saúde, no âmbito dos Estados e do Distrito Federal, pela respectiva Secretaria de Desenvolvimento e Cidadania ou órgão equivalente; e, no âmbito dos Municípios, pela respectiva Secretaria de Saúde ou órgão equivalente.");
            c.setText("no âmbito da União, pelo Fundo Nacional de Saúde, no âmbito dos Estados e do Distrito Federal, pela respectiva Secretaria de Saúde ou órgão equivalente e no âmbito dos Municípios, pela respectiva Secretaria de Saúde ou órgão equivalente.");
            d.setText("no âmbito da União, pelo Ministério da Saúde, no âmbito dos Estados e do Distrito Federal, pela respectiva Secretaria de Saúde ou órgão equivalente e, no âmbito dos Municípios, pela respectiva Secretaria de Saúde ou órgão equivalente.");
            e.setText("no âmbito da União, pelo Ministério da Saúde, no âmbito dos Estados e do Distrito Federal, pela respectiva Secretaria de Saúde ou órgão equivalente e, no âmbito dos Municípios, pela respectiva Secretaria de Desenvolvimento e Cidadania ou órgão equivalente.");
            alt = R.id.d;
        }
        if (question == 23) {
            questaoTextView.setText("Quem poderia se beneficiar da assistência à saúde desenvolvida pelo INAMPS, antes da criação do SUS?");
            a.setText("Apenas os trabalhadores informais, sem “carteira assinada”, e seus dependentes, ou seja, não tinha o caráter universal.");
            b.setText("Todos os trabalhadores tanto da economia formal como os informais e seus dependentes, ou seja, tinha o caráter universal.");
            c.setText("Apenas os funcionários públicos da União e seus dependentes, ou seja, não tinha o caráter universal.");
            d.setText("Apenas os trabalhadores da economia formal, com “carteira assinada”, e seus dependentes, ou seja, não tinha o caráter universal.");
            e.setText("A toda população indiscriminadamente, demonstrando assim o caráter universal da assistência.");
            alt = R.id.d;
        }
        if (question == 24) {
            questaoTextView.setText("Para ser instituída, a Região de Saúde deve conter, no mínimo, ações e serviços de");
            a.setText("atenção primária, atenção psicossocial, atenção ambulatorial especializada e hospitalar e vigilância sanitária.");
            b.setText("urgência e emergência, atenção psicossocial, vigilância sanitária e atenção ambulatorial especializada e hospitalar.");
            c.setText("atenção primária, urgência e emergência, atenção epidemiológica, atenção ambulatorial especializada e hospitalar e vigilância em saúde. ");
            d.setText("vigilância sanitária, atenção primária, urgência e emergência, atenção psicossocial, atenção ambulatorial especializada e hospitalar.");
            e.setText("atenção primária, urgência e emergência, atenção psicossocial, atenção ambulatorial especializada e hospitalar e vigilância em saúde.");
            alt = R.id.e;
        }
        if (question == 25) {
            questaoTextView.setText("O acesso universal e igualitário à assistência farmacêutica pressupõe, cumulativamente:");
            a.setText("estar o usuário assistido por ações e serviços de saúde do SUS, ter o medicamento sido prescrito por profissional de saúde, no exercício regular de suas funções no SUS, estar a prescrição em conformidade com a RENAME e os Protocolos Clínicos e Diretrizes Terapêuticas ou com a relação específica complementar estadual, distrital ou municipal de medicamentos e ter a dispensação ocorrido em unidades indicadas pela direção do SUS.");
            b.setText("estar a prescrição em conformidade com a RENAME e os Protocolos Clínicos e Diretrizes Terapêuticas ou com a relação específica complementar estadual, distrital ou municipal de medicamentos, porém devido ao acesso universal e igualitário os medicamentos poderão ser prescritos por todos e quaisquer médicos no exercício regular da profissão e atingindo a toda a população.");
            c.setText("o usuário, devido ao acesso universal e igualitário, não necessita estar assistido por ações e serviços de saúde do SUS, porém o medicamento deverá ter sido prescrito por profissional de saúde, no exercício regular de suas funções no SUS, estar a prescrição em conformidade com a RENAME e os Protocolos Clínicos e Diretrizes Terapêuticas ou com relação específica complementar estadual, distrital bou municipal de medicamentos e ter a dispensação ocorrido em unidades indicadas pela direção do SUS.");
            d.setText("estar a prescrição em conformidade com a RENAME e os Protocolos Clínicos e Diretrizes Terapêuticas ou com a relação específica complementar apenas no âmbito distrital, de medicamentos e ter a  dispensação ocorrido em unidades indicadas pela direção do SUS, devendo estar, o usuário, assistido por ações e serviços de saúde do SUS.");
            e.setText("estar o usuário assistido por ações e serviços de saúde do SUS, porém, devido ao acesso universal e igualitário à assistência farmacêutica, a prescrição da medicação não necessita dos Protocolos Clínicos e seguir as Diretrizes Terapêuticas.");
            alt = R.id.a;
        }
        if (question == 26) {
            questaoTextView.setText("De acordo com as diretrizes da Resolução 453/2012 do Conselho Nacional da Saúde, o Plenário dos Conselhos de Saúde");
            a.setText("se reunirá, no mínimo, a cada mês e, extraordinariamente, quando necessário, e terá como base o seu Regimento Interno. A pauta e o material de apoio às reuniões devem ser encaminhados aos conselheiros com antecedência mínima de 10 (dez) dias.");
            b.setText("se reunirá, no mínimo, a cada quatro meses e, extraordinariamente, quando necessário, e terá como base o seu Regimento Interno. A pauta e o material de apoio às reuniões devem ser encaminhados aos conselheiros com antecedência mínima de 15 (quinze) dias.");
            c.setText("se reunirá, no mínimo, uma vez por semana e, extraordinariamente, quando necessário, e terá como base o seu Regimento Interno. A pauta e o material de apoio às reuniões devem ser encaminhados aos conselheiros com antecedência mínima de 24 (vinte e quatro) horas.");
            d.setText("se reunirá em todos os finais de semana e terá como base o seu Regimento Interno. A pauta e o material de apoio às reuniões devem ser encaminhados aos conselheiros com antecedência mínima de 24 (vinte e quatro) horas.");
            e.setText("se reunirá, no mínimo, a cada ano e, extraordinariamente, quando necessário, e terá como base o seu Regimento Interno. A pauta e o material de apoio às reuniões devem ser encaminhados aos conselheiros com antecedência mínima de 5 (cinco) dias.");
            alt = R.id.a;
        }
        if (question == 27) {
            questaoTextView.setText("De acordo com o que expressa a Constituição Federal, no que tange a participação da iniciativa privada na assistência à saúde, assinale a alternativa correta.");
            a.setText("As instituições privadas não poderão participar do Sistema Único de Saúde. Somente será possível, segundo diretrizes deste e mediante convênio, participação de entidades filantrópicas e as sem fins lucrativos.");
            b.setText("As instituições privadas poderão participar de forma complementar do sistema único de saúde, segundo diretrizes deste, mediante contrato de direito público ou convênio, vedada a participação de entidades filantrópicas e as sem fins lucrativos.");
            c.setText("As instituições privadas poderão participar do Sistema Único de Saúde somente de forma subsidiária, quando não houver serviço público disponível, mediante contrato de direito público, tendo preferência as entidades filantrópicas e as sem fins lucrativos.");
            d.setText("As instituições privadas poderão participar do Sistema Único de Saúde somente de forma subsidiária, quando não houver serviço público disponível, mediante convênio com as entidades filantrópicas e as sem fins lucrativos.");
            e.setText("As instituições privadas poderão participar de forma complementar do Sistema Único de Saúde, segundo diretrizes deste, mediante contrato de direito público ou convênio, tendo preferência as entidades filantrópicas e as sem fins lucrativos.");
            alt = R.id.e;
        }
        if (question == 28) {
            questaoTextView.setText("De acordo com as disposições da Lei Orgânica da Saúde (Lei 8.080/90), a incorporação, a exclusão ou a alteração pelo SUS de novos medicamentos, produtos e procedimentos, bem como a constituição ou a alteração de protocolo clínico ou de diretriz terapêutica são atribuições");
            a.setText("do Conselho da Saúde, assessorado pelo Ministério de Ciências e Tecnologia.");
            b.setText("do Ministério da Ciência e Tecnologia, assessorado pela Conferência Nacional de Saúde.");
            c.setText("do Ministério da Saúde, assessorado pelo Conselho Nacional de Saúde.");
            d.setText("do Ministério da Saúde, assessorado pela Comissão Nacional de Incorporação de Tecnologias no SUS.");
            e.setText("exclusivas do Município, assessorado pela Conferência Nacional de Saúde.");
            alt = R.id.d;
        }
        if (question == 29) {
            questaoTextView.setText("De acordo com o que expressa a Lei 8.142/90, os recursos do Fundo Nacional de Saúde (FNS) alocados como cobertura das ações e serviços de saúde a serem implementados pelos Municípios, Estados e Distrito Federal serão ");
            a.setText("destinados, pelo menos sessenta por cento, aos Municípios, afetando-se o restante aos Estados.");
            b.setText("destinados, pelo menos sessenta por cento, aos Estados, afetando-se o restante aos Municípios.");
            c.setText("destinados, pelo menos setenta por cento, aos Municípios, afetando-se o restante aos Estados.");
            d.setText("destinados, pelo menos setenta por cento, aos Estados, afetando-se o restante aos Municípios.");
            e.setText("divididos igualitariamente.");
            alt = R.id.c;
        }
        if (question == 30) {
            questaoTextView.setText("De acordo com as definições do Decreto Presidencial nº 7.508/2011, assinale a alternativa correta.");
            a.setText("Portas de Entrada são instâncias de pactuação consensual entre os entes federativos para definição das regras da gestão compartilhada do SUS.");
            b.setText("A Relação Nacional de Ações e Serviços de Saúde (RENASES) compreende todas as ações e serviços que o SUS oferece ao usuário para atendimento da integralidade da assistência à saúde.");
            c.setText("A Conferência Nacional de Saúde, em conjunto com o Poder Legislativo, estabelece as diretrizes a serem observadas na elaboração dos planos de saúde, de acordo com as características epidemiológicas e da organização de serviços nos entes federativos e nas Regiões de Saúde.");
            d.setText("O processo de planejamento da saúde será descendente e independente, desde o nível federal até o local, devendo, no entanto, ser ouvidas as respectivas Conferências de Saúde, compatibilizando-se as necessidades das políticas de saúde com a disponibilidade de recursos financeiros.");
            e.setText("O Conselho de Saúde é o órgão competente para dispor sobre a Relação Nacional de Medicamentos Essenciais (RENAME) e os Protocolos Clínicos e Diretrizes Terapêuticas em âmbito nacional.");
            alt = R.id.b;
        }
        if (question == 31) {
            questaoTextView.setText("Assinale a alternativa correta.");
            a.setText("Constitui, o Sistema Único de Saúde, o conjunto de ações e serviços de saúde, prestados por órgãos e instituições públicas federais, estaduais e municipais, da Administração direta e indireta e das fundações mantidas pelo Poder Público.");
            b.setText("A iniciativa privada poderá participar do Sistema Único de Saúde, em caráter concorrente com a iniciativa pública.");
            c.setText("Não estão incluídas no Sistema Único de Saúde as instituições públicas de controle de qualidade, pesquisa e produção de insumos, medicamentos, inclusive de sangue e hemoderivados, e de equipamentos para saúde.");
            d.setText("Não são objetivos do Sistema Único de Saúde (SUS) as atividades preventivas.");
            e.setText("O desenvolvimento de políticas econômicas não tem relação com os objetivos do SUS.");
            alt = R.id.a;
        }
        if (question == 32) {
            questaoTextView.setText("Assinale a alternativa que NÃO apresenta um princípio ou diretrizes do Sistema Único de Saúde.");
            a.setText("Direito à informação, às pessoas assistidas, sobre sua saúde, exceto em casos de doença terminal.");
            b.setText("Divulgação de informações quanto ao potencial dos serviços de saúde e a sua utilização pelo usuário.");
            c.setText("Descentralização político-administrativa, com direção única em cada esfera de governo.");
            d.setText("Integração em nível executivo das ações de saúde, meio ambiente e saneamento básico.");
            e.setText("Preservação da autonomia das pessoas na defesa de sua integridade física e moral.");
            alt = R.id.a;
        }
        if (question == 33) {
            questaoTextView.setText("Assinale a alternativa correta.");
            a.setText("Em sua constituição, o Sistema Único de Saúde não tem a participação da iniciativa privada.");
            b.setText("As Regiões de Saúde serão instituídas pelo Estado, em articulação com os Municípios, inexistindo Regiões de Saúde interestadual.");
            c.setText("A instituição das Regiões de Saúde observará cronograma no Plano Plurianual do Governo Federal");
            d.setText("As Regiões de Saúde não serão referência para as transferências de recursos entre os entes federativos.");
            e.setText("Para ser instituída, a Região de Saúde deve conter, no mínimo, ações e serviços de atenção primária, urgência e emergência, atenção psicossocial, atenção ambulatorial especializada e hospitalar e vigilância em saúde.");
            alt = R.id.e;
        }
        if (question == 34) {
            questaoTextView.setText("Quanto à seguridade social, assinale a alternativa correta.");
            a.setText("A saúde é direito de todos e dever do Estado, garantido mediante políticas sociais e econômicas, com acesso preferencial aos mais pobres.");
            b.setText("As ações e serviços públicos de saúde integram uma rede centralizada e constitui um sistema único.");
            c.setText("A União aplicará anualmente, em ações e serviços públicos de saúde, valor não inferior a 15% (quinze por cento) da receita corrente líquida do respectivo exercício financeiro.");
            d.setText("As ações e serviços públicos de saúde não contarão com a participação da comunidade");
            e.setText("A assistência à saúde é exclusiva do Poder Público.");
            alt = R.id.c;
        }
        if (question == 35) {
            questaoTextView.setText("Quanto à seguridade social, assinale a alternativa correta.");
            a.setText("As instituições privadas poderão participar de forma subsidiária do Sistema Único de Saúde.");
            b.setText("É permitida a destinação de recursos públicos para auxiliar as instituições privadas com fins lucrativos.");
            c.setText("É vedada a participação direta ou indireta de empresas ou capitais estrangeiros na assistência à saúde do País, salvo nos casos previstos em lei.");
            d.setText("Não compete ao Sistema Único de Saúde ordenar a formação de recursos humanos na área de saúde.");
            e.setText("Ao Sistema Único de Saúde, compete colaborar na proteção do meio ambiente, exceto o meio ambiente do trabalho.");
            alt = R.id.c;
        }
        if (question == 36) {
            questaoTextView.setText("Quando uma Secretaria de Saúde investe mais recursos onde há maior carência na tentativa de diminuir as desigualdades, ela está agindo em consonância com o princípio da:");
            a.setText("universalidade");
            b.setText("equidade");
            c.setText("descentralização");
            d.setText("intersetorialidade");
            e.setText("integralidade");
            alt = R.id.b;
        }
        if (question == 37) {
            questaoTextView.setText("De acordo com a Lei nº 8.142/90, os Conselhos de Saúde contam com a representação dos seguintes segmentos:");
            a.setText("gestores de saúde e representantes das associações de moradores");
            b.setText("representantes do governo, de portadores de patologias e do setor privado");
            c.setText("representantes do governo, de portadores de patologias e do setor privado");
            d.setText("representantes do governo, prestadores de serviços, profissionais de saúde e usuários");
            e.setText("gestores públicos e prestadores de serviços");
            alt = R.id.d;
        }
        if (question == 38) {
            questaoTextView.setText("Regionalização do Sistema de Saúde significa organização por");
            a.setText("programas específicos por patologias");
            b.setText("áreas geográficas distintas");
            c.setText("critérios de vigilância epidemiológica");
            d.setText("níveis diferentes de gestão");
            e.setText("níveis de complexidade tecnológica");
            alt = R.id.b;
        }
        if (question == 39) {
            questaoTextView.setText("Compõem a equipe mínima de Saúde da Família:");
            a.setText("médico, enfermeiro, psicólogo e agente comunitário de saúde");
            b.setText("enfermeiro, dentista, psicólogo e auxiliar ou técnico de enfermagem");
            c.setText("médico, enfermeiro, dentista e auxiliar ou técnico de enfermagem");
            d.setText("médico, dentista, psicólogo e agente comunitário de saúde");
            e.setText("médico, enfermeiro, auxiliar ou técnico de enfermagem e agente comunitário de saúde");
            alt = R.id.e;
        }
        if (question == 40) {
            questaoTextView.setText("A Atenção Básica tem como princípio");
            a.setText("ser a porta de entrada preferencial do paciente na rede assistencial de saúde");
            b.setText("priorizar ações curativas em detrimento das ações de promoção da saúde");
            c.setText("prover assistência em grandes hospitais gerais e especializados");
            d.setText("ser local de referência para assistência em nível terciário");
            e.setText("desenvolver ações apenas nas unidades de saúde");
            alt = R.id.a;
        }
        if (question == 41) {
            questaoTextView.setText("Segundo a Lei nº 8.080, de 19 de setembro de 1990, são princípios do SUS, EXCETO:");
            a.setText("universalidade de acesso aos serviços de saúde");
            b.setText("ordenação da formação de recursos humanos");
            c.setText("integralidade da assistência");
            d.setText("participação da comunidade");
            e.setText("descentralização político-administrativa");
            alt = R.id.b;
        }
        if (question == 42) {
            questaoTextView.setText("Segundo os princípios e diretrizes do SUS, qual das unidades listadas a seguir deve ser a porta de entrada preferencial e a referência do cidadão no sistema de saúde?");
            a.setText("Laboratório de Saúde Pública");
            b.setText("Hospital");
            c.setText("Centro de Especialidades");
            d.setText("Unidade Básica de Saúde");
            e.setText("Serviço deApoio Diagnóstico e Terapêutico");
            alt = R.id.d;
        }
        if (question == 43) {
            questaoTextView.setText("A taxa de incidência de sarampo em um município estima o risco de:");
            a.setText("as pessoas acometidas pela doença virem a falecer.");
            b.setText("as pessoas acometidas pela doença apresentarem sequelas permanentes.");
            c.setText("seus habitantes contraírem a doença.");
            d.setText("seus habitantes falecerem por causa da doença.");
            e.setText("se identificar todos os casos da doença existentes na população.");
            alt = R.id.c;
        }
        if (question == 44) {
            questaoTextView.setText("O denominador da taxa de mortalidade geral é constituído por:");
            a.setText("população");
            b.setText("nascidos vivos");
            c.setText("mortes");
            d.setText("pessoas doentes");
            e.setText("natimortos");
            alt = R.id.a;
        }
        if (question == 45) {
            questaoTextView.setText("Assinale a alternativa que apresenta uma medida de prevenção primária segundo o modelo da história natural da doença .");
            a.setText("Iniciar antibiótico para paciente com pneumonia.");
            b.setText("Realizar cirurgia em paciente com apendicite");
            c.setText("Instituir um programa antitabagismo para funcionários.");
            d.setText("Proporcionar atendimento fisioterápico em pacientes que tiveramAVC.");
            e.setText("Oferecer reabilitação cardíaca para pessoas que tiveram infarto do miocárdio.");
            alt = R.id.c;
        }
        if (question == 46) {
            questaoTextView.setText("Segundo a Portaria nº 2.472, de 31de agosto de 2010, do Ministério da Saúde, são doenças que fazem parte da Lista de Notificação Compulsória, EXCETUANDO-SE:");
            a.setText("coqueluche");
            b.setText("dengue");
            c.setText("hepatites virais");
            d.setText("tuberculose");
            e.setText("varicela");
            alt = R.id.e;
        }
        if (question == 47) {
            questaoTextView.setText("O Sistema Único de Saúde SUS conta, em cada esfera do governo, sem prejuízo das funções do Poder Legislativo, com as seguintes instâncias colegiadas:");
            a.setText("Assembleia Deliberativa de Usuários e Comissão Gestora de Saúde.");
            b.setText("Comissão Gestora de Saúde e Conselho de Saúde.");
            c.setText("Conselho de Saúde e Assembleia Deliberativa de Usuários.");
            d.setText("Comissão Gestora de Saúde e Conferência de Saúde.");
            e.setText("Conferência de Saúde e Conselho de Saúde.");
            alt = R.id.e;
        }
        if (question == 48) {
            questaoTextView.setText("O repasse direto e automático de recursos para a cobertura das ações e serviços de saúde a serem implementados pelos Municípios está condicionado ao cumprimento de algumas exigências, entre elas: ");
            a.setText("a contrapartida de recursos para a saúde no respectivo orçamento.");
            b.setText("o estabelecimento de acordo de compra e venda de serviços.");
            c.setText("o comprovado crescimento de suas populações.");
            d.setText("a identificação das diferenças entre as diversas regiões.");
            e.setText("a celebração de convênio entre os órgãos executivos.");
            alt = R.id.a;
        }
        if (question == 49) {
            questaoTextView.setText("NÃO compõem o Conselho Municipal de Saúde os representantes:");
            a.setText("do governo");
            b.setText("dos prestadores de serviço");
            c.setText("dos profissionais de saúde");
            d.setText("das indústrias biomédicas");
            e.setText("dos usuários");
            alt = R.id.d;
        }
        if (question == 50) {
            questaoTextView.setText("Os recursos federais destinados às ações e aos serviços de saúde são organizados e transferidos na forma de blocos de financiamento. Assinale a alternativa que NÃO apresenta um desses blocos.");
            a.setText("Atenção Básica");
            b.setText("Atenção de Média e Alta Complexidade Ambulatorial e Hospitalar");
            c.setText("Vigilância em Saúde");
            d.setText("Assistência Farmacêutica");
            e.setText("Atenção à Saúde do Trabalhador");
            alt = R.id.e;
        }
        return alt;
    }
}
