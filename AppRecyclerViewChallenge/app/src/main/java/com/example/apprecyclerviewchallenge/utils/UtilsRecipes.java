package com.example.apprecyclerviewchallenge.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;

import com.example.apprecyclerviewchallenge.R;
import com.example.apprecyclerviewchallenge.model.Recipe;

import java.util.ArrayList;

public class UtilsRecipes extends AppCompatActivity {

    Resources resources;
    ArrayList<Recipe> recipeList = new ArrayList<>();

    public UtilsRecipes(Resources resources) {
        this.resources = resources;
    }

    public ArrayList<Recipe> getAllRecipes(){

        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.paozinho_de_tapioca_com_3_ingredientes),
                "Pãozinho de tapioca simples leva apenas 3 ingredientes perfeito para toda família",
                "Com apenas três ingredientes, vamos preparar uma incrível receita de pãozinho de tapioca que é surpreendentemente gostoso, ainda mais por ser tão fácil e simples de se preparar. O pãozinho de tapioca é uma opção muito prática e saborosa para fazer e comer com a família na hora do lanche ou do café.",
                new String[]{
                        "500g de goma de tapioca \n",
                        "200g de muçarela ralada\n",
                        "1 ovo"},
                new String[]{
                        "Em uma tigela, adicione a goma de tapioca, a muçarela ralada e o ovo.",
                        "Misture bem até formar uma massa lisa e homogênea.",
                        "Modele em formato cilíndrico.",
                        "Acomode os pãezinhos em uma assadeira.",
                        "Leve ao forno a 200°C, preaquecido, e asse por 15 minutos.",
                        "Prontinho! Agora é só saborear esse delicioso pãozinho de tapioca!"
                }));

        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.sobremesa_leite_ninho_facil),
                "Sobremesa de Leite Ninho fácil para o ano novo uma sobremesa deliciosa que faço todo ano",
                "A sobremesa de leite ninho é muito fácil de fazer, a além disso é super refrescante e muito saborosa. Tem uma textura extremamente macia que derrete na boca. Siga o passo a passo dessa versão de sobremesa de leite ninho e você vai se surpreender com o resultado magnífico!",
                new String[]{
                        "½ xícara(chá) de água quente quase fervendo \n",
                        "½ xícara(chá) de açúcar cristal\n",
                        "1 ½ xícara(chá) de leite ninho",
                        "2 caixinhas de creme de leite gelado",
                        "2 envelopes de gelatina incolor sem sabor",
                        "10 colheres(sopa) de água ",
                        "Granulado de chocolate branco para decorar"
                },
                new String[]{
                        "No liquidificador coloque o leite ninho, a água quente e o açúcar. Bata por aproximadamente 3 minutos até obter uma mistura consistente e homogênea.",
                        "Reserve.",
                        "Numa tigelinha hidrate a gelatina na água e dissolva conforme instruções da embalagem.",
                        "Coloque no liquidificador o creme de leite bem gelado e bata por cerca de 3 minutos até que quase dobre de volume e ganhe consistência. ",
                        "Adicione o leite condensado fake e o leite ninho e bata muito bem até obter um creme homogêneo.",
                        "Acrescente a gelatina dissolvida e bata apenas para incorporar. Despeje a mistura numa travessa e leve para gelar por cerca de 1 hora até que fique firme.",
                        "Retire da geladeira, decore com granulado de chocolate branco e retorne para gelar por mais 2 horas. ",
                        "E agora é só retirar, e saborear a mais deliciosa sobremesa de leite ninho do planeta!"
                }));


        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.bolo_amanteigado_de_cafe_colonial),
                "Bolo amanteigado de café colonial ele desmancha na boca e é bem fofinho",
                "O bolo amanteigado, que é bem simples e prático de fazer, fica delicioso. Essa receita de vó é sensacional e o resultado é um bolo que derrete na boca, de tão macio, perfeito para acompanhar seu café, chá ou lanche da tarde. Confira a receita, faça o incrível bolo amanteigado e receba os elogios!",
                new String[]{
                        "1 xícara (chá) de manteiga",
                        "1 colher (chá) de essência de amêndoa",
                        "5 ovos inteiros",
                        "1 colher (chá) de sal",
                        "1 colher (chá) de essência de baunilha",
                        "1 e ½ colher (sopa) de fermento em pó",
                        "3 xícaras (chá) de farinha de trigo",
                        "2 xícaras (chá) de açúcar",
                        "1 e ½ xícara (chá) de leite integral"
                },
                new String[]{
                        "Para formar um creme homogêneo e fofo, adicione na batedeira e bata: os ovos, o açúcar, a manteiga e o sal.",
                        "Junte as essências de amêndoa e de baunilha e bata mais um pouco.",
                        "Acrescente a farinha de trigo aos poucos, intercalando com o leite, sem parar de bater.",
                        "Coloque o fermento em pó por último e mexa suavemente com uma colher.",
                        "Transfira a mistura para uma forma (22cm de diâmetro) de buraco central, untada e enfarinhada.",
                        "Leve para assar em forno médio, pré-aquecido a 180°C, por cerca de 45 minutos.",
                        "Aguarde amornar para depois desenformar.",
                        "Sirva e se delicie com seu sensacional bolo amanteigado!"
                }));


        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.salpicao_de_frango_simples),
                "Salpicão de frango fácil e delicioso perfeito para acompanhar sua festa de ano novo",
                "O salpicão de frango precisa estar presente em sua mesa de Natal pois fica simplesmente divino e é um ótimo acompanhamento para seus pratos. Combinando os ingredientes com perfeição, esse salpicão fica leve e muito saboroso. Confira a receita do Canal Magia do Sabor e faça o magnífico salpicão de frango, que todos vão amar!",
                new String[]{
                        "5 colheres (sopa) bem cheias de maionese",
                        "160g de batata-palha",
                        "azeite de oliva (a gosto)",
                        "½ cebola picadinha",
                        "4 cenouras médias raladas",
                        "2 dentes de alho picados",
                        "1 lata de milho-verde em conserva escorrido",
                        "½ colher (sopa) de colorau",
                        "1 lata de ervilha em conserva escorrida",
                        "120g de azeitonas sem caroço fatiadas",
                        "½ xícara (chá) de água",
                        "500g de frango cozido e desfiado"
                },
                new String[]{
                        "Para formar um creme homogêneo e fofo, adicione na batedeira e bata: os ovos, o açúcar, a manteiga e o sal.",
                        "Assim que dourar, junte a água e o colorau, misturando bem.",
                        "Acrescente o frango desfiado e misture até que toda a água seque.",
                        "Em seguida, apague o fogo e aguarde esfriar.",
                        "Adicione o frango e a cenoura ralada em um recipiente grande, e mexa.",
                        "Na sequência, coloque o milho, a ervilha e as azeitonas e torne a mexer.",
                        "Utilize azeite a gosto para regar.",
                        "Coloque a maionese e misture para que todos os ingredientes se incorporem.",
                        "Junte a batata-palha por último e mais uma vez mexa tudo.",
                        "Transfira para uma travessa, sirva e saboreie seu delicioso salpicão de frango!"
                }));


        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.pudim_de_geladeira_sem_ovo),
                "Pudim sem ovo e sem forno é só misturar os ingredientes e levar para geladeira",
                "Nada melhor do que fazer um pudim sem ovo e sem forno! Isso mesmo! Confira o passo a passo da receita e faça hoje mesmo esse espetacular pudim sem ovo e sem forno, que não perde nada para o tradicional! Fica incrivelmente delicioso, macio e você vai se apaixonar e fazer sempre!",
                new String[]{
                        "10 colheres de açúcar",
                        "120 ml de leite morno",
                        "1 caixinha de leite condensado",
                        "2 caixinhas de creme de leite",
                        "1 envelope de gelatina incolor sem sabor "
                },
                new String[]{
                        "Numa forma (20 cm de diâmetro) de furo central coloque o açúcar, leve ao fogo baixo até derreter totalmente e obter um caramelo dourado e brilhante.",
                        "Caramelize o fundo e as laterais da forma e reserve.",
                        "No liquidificador coloque o leite condensado, o creme de leite e bata por 1 minuto. Adicione a gelatina incolor dissolvida no leite morno e bata por mais 5 minutos.",
                        "Em seguida, apague o fogo e aguarde esfriar.",
                        "Adicione o frango e a cenoura ralada em um recipiente grande, e mexa.",
                        "Despeje essa mistura na forma caramelizada, e leve para gelar por 5 horas. ",
                        "Retire da geladeira, desenforme e se delicie com esse maravilhoso pudim sem ovo e sem forno!",
                }));

        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.pao_sem_farinha_de_trigo_levinho),
                "Pão sem farinha de trigo levinho e delicioso para o café da manhã ou lanche da tarde",
                "Que tal inovar no seu lanche ou café preparando um delicioso pão sem farinha? Esse pão sem farinha fica super fofinho e muito saboroso, além de ser totalmente livre de glúten. Uma opção saborosa e mais saudável para o seu café do que o pão tradicional. Tudo isso com ingredientes simples e um modo de preparo fácil. ",
                new String[]{
                        "250g de tapioca hidratada",
                        "100g de amido de milho",
                        "130g de queijo ralado ",
                        "1 colher de café de sal",
                        "1 colher de sobremesa de manteiga ou margarina",
                        "1 ovo",
                        "90mL de leite",
                        "1 colher de chá de fermento químico em pó",
                        "muçarela ralada a gosto"
                },
                new String[]{
                        "Adicione a tapioca, o amido, o queijo ralado, o sal, o fermento, a margarina e o ovo em uma tigela e misture.",
                        "Adicione o leite aos poucos, sem parar de mexer.",
                        "Depois de misturar todos os ingredientes, despeje em uma forma de pão untada com óleo.",
                        "Espalhe a muçarela ralada por cima. ",
                        "Asse em forno a 200°C, preaquecido, por aproximadamente 35 minutos.",
                        "Agora é só se deliciar com essa maravilha de pão sem farinha! "
                }));


        recipeList.add(new Recipe(
                BitmapFactory.decodeResource(resources, R.raw.quiche_palmito_com_queijo),
                "Quiche de palmito fácil uma delícia para o seu lanche ou jantar",
                "Essa receita de quiche de palmito com queijo é uma delícia por completo, pois conta com uma massa saborosíssima e que desmancha na boca, além de um recheio super cremoso e muito bem preparado. Você não pode deixar de fazer essa sensacional quiche de palmito com queijo para toda a família! ",
                new String[]{
                        "1 caixinha de creme de leite",
                        "200g de margarina",
                        "2 ovos",
                        "½ colher de sopa de sal",
                        "600g de farinha de trigo",
                        "2 vidros de palmito (600g)",
                        "1 cebola picada",
                        "1 tomate picado",
                        "500mL de leite integral",
                        "2 colheres de sopa de farinha de trigo",
                        "300g de requeijão",
                        "250g de muçarela em cubos",
                        "40g de parmesão ralado"
                },
                new String[]{
                        "Em um bowl, adicione o creme de leite, o sal, os ovos e a margarina. Misture bem.",
                        "Adicione a farinha de trigo aos poucos, sem parar de mexer.",
                        "Depois de incorporar toda a farinha, faça uma bola com a massa, cubra e deixe descansar por 30 minutos.",
                        "Em uma panela com óleo, coloque a cebola e o tomate para refogar.",
                        "Acrescente o palmito e mexa.",
                        "Dilua a farinha de trigo no leite e despeje na panela.",
                        "Mexa até engrossar.",
                        "Desligue o fogo, adicione o requeijão e misture bem. Reserve.",
                        "Forre o fundo e as laterais de uma forma redonda de fundo removível com toda a massa.",
                        "Despeje o recheio e cubra com a muçarela e o parmesão.",
                        "Leve ao forno a 200°C, preaquecido, e asse por aproximadamente 20 minutos.",
                        "Sirva sua espetacular quiche de palmito com queijo!"
                }));


        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.escondidinho_de_frango),
                "Escondidinho de frango cremoso um prato delicioso para servir no seu fim de ano",
                "Essa receita de escondidinho de frango cremoso é uma excelente opção para fazer naquele agradável almoço de domingo com toda a família reunida. Se você fizer, com certeza todo mundo vai te pedir a receita porque vai querer fazer também. Não tem como não gostar desse escondidinho de frango cremoso!",
                new String[]{
                        "1 colher de sopa de margarina",
                        "½ cebola picada",
                        "2 dentes de alho picados",
                        "1 peito de frango cozido e desfiado",
                        "sal e pimenta-do-reino a gosto",
                        "½ copo de molho de tomate",
                        "2 colheres de cheiro-verde picado",
                        "1kg de batatas cozidas e descascadas",
                        "2 colheres de sopa de margarina",
                        "½ cebola picada",
                        "1 copo de leite",
                        "1 colherinha de café de sal",
                        "pimenta-do-reino a gosto",
                        "200g de muçarela ralada",
                        "200g de muçarela ralada"
                },
                new String[]{
                        "Em uma panela, derreta a margarina e coloque o alho e a cebola para dourar.",
                        "Junte o frango desfiado e tempere com sal e pimenta-do-reino. ",
                        "Adicione o molho de tomate e mexa.",
                        "Desligue o fogo e acrescente o cheiro-verde picado. Reserve.",
                        "Amasse bem todas as batatas.",
                        "Refogue a cebola em uma panela com manteiga.",
                        "Junte as batatas amassadas e adicione o leite aos poucos, sem parar de mexer.",
                        "Tempere com sal e pimenta-do-reino.",
                        "Quando estiver com uma textura lisa e homogênea, desligue o fogo.",
                        "Forre uma travessa refratária com metade do purê.",
                        "Espalhe por cima o frango desfiado.",
                        "Cubra com o restante do purê.",
                        "Polvilhe a muçarela e orégano por cima.",
                        "Leve ao forno a 180°C, preaquecido, por 30 minutos ou até gratinar.",
                        "Sirva sem maravilhoso escondidinho de frango!"
                }
        ));

        recipeList.add(new Recipe( BitmapFactory.decodeResource(resources,R.raw.lanche_frigideira),
                "Lanche de frigideira recheado sem farinha uma refeição pronta em poucos minutos",
                "Essa receita de lanche de frigideira sem farinha é uma delícia sem igual e muito fácil e prático de se fazer. Esse lanche é uma opção para fazer e comer com a família ou com os amigos em um momento descontraído. Faça hoje mesmo esse lanche de frigideira sem farinha e surpreenda-se com tanto sabor! ",
                new String[]{
                        "2 colheres de sopa de óleo",
                        "½ cebola picada",
                        "2 dentes de alho picados",
                        "2 tomates picados",
                        "300g de carne seca dessalgada cozida e desfiada",
                        "pimenta-do-reino a gosto",
                        "cheiro-verde picado a gosto",
                        "1 colher de sopa de requeijão ",
                        "800g de mandioca",
                        "sal a gosto",
                        "1 colher de sopa de manteiga",
                        "cheiro-verde picado a gosto",
                        "100g de muçarela","orégano a gosto",
                        "tomate em rodelas a gosto"
                },
                new String[]{
                        "Em uma panela com óleo, refogue o alho, a cebola e o tomate.",
                        "Adicione a carne seca e misture bem.",
                        "Tempere com pimenta-do-reino e cheiro-verde.",
                        "Adicione o requeijão e misture bem. Reserve.",
                        "Descasque a mandioca, corte em cubos e retire a fibra central.",
                        "Cozinhe em uma panela com água fervente até que esteja macia.",
                        "Escorra e passe para uma tigela.",
                        "Junte a manteiga e amasse bem, até ficar em textura de purê.",
                        "Tempere com cheiro-verde, sal e orégano.",
                        "Forre o fundo de uma frigideira antiaderente com o purê, espalhe recheio de carne seca por cima e cubra com mais purê.",
                        "Depois que dourar e desgrudar de uma lado, com cuidado vire e deixe dourar do outro.",
                        "Quando virar, cubra com fatias de muçarela, rodelas de tomate e polvilhe orégano a gosto.",
                        "Enfim, é só servir e se deliciar com esse incrível lanche de frigideira!"
                }
        ));

        recipeList.add(new Recipe( BitmapFactory.decodeResource(resources,R.raw.bolinho_bacalhau),
                "Bolinho de bacalhau delicioso receita que aprendi com o português da salgaderia",
                "Essa receita de bolinho de bacalhau é uma verdadeira iguaria e um aperitivo perfeito para comer com os amigos durante um agradável momento de confraternização. O preparo desse bolinho de bacalhau é muito simples e fácil e o resultado é surpreendentemente gostoso. Não deixe de preparar em casa! ",
                new String[]{
                        "1kg de bacalhau dessalgado cozido e desfiado",
                        "1kg de batata asterix cozida",
                        "150g de azeitonas picadas","1 xícara de chá de cheiro-verde picado",
                        "1 ovo",
                        "1 xícara de chá de farinha de rosca",
                        "2 cebolas picadas","2 dentes de alho",
                        "100mL de azeite"
                },
                new String[]{
                        "Em uma panela, adicione o azeite e coloque o alho e a cebola para refogar.",
                        "Quando dourar, despeje o bacalhau desfiado.",
                        "Mexa bem e deixe reduzir o líquido do bacalhau. Desligue o fogo e reserve.",
                        "Amasse bem as batatas, até formar uma massa lisa e homogênea.",
                        "Em uma tigela, adicione o bacalhau, as batatas amassadas, as azeitonas, o cheiro-verde, o ovo e a farinha de rosca.",
                        "Misture bem até ficar homogêneo.",
                        "Modele os bolinhos com as mãos.",
                        "Frite os bolinhos em uma panela com óleo quente, até dourar.",
                        "Se preferir, congele os bolinhos e frite quando quiser.",
                        "Seus incríveis bolinhos de bacalhau estão prontos!"
                }
        ));

        recipeList.add(new Recipe( BitmapFactory.decodeResource(resources,R.raw.pudim_de_coco_simples),
                "Pudim de coco simples que não vai ao forno muita praticidade nesse fim de ano",
                "O pudim de coco delicioso além de ser uma sobremesa mega saborosa, é a opção perfeita para ser servida após um almoço ou um jantar. Com uma textura muito macia que derrete na boca, e o sabor inigualável do coco, resulta no pudim de coco delicioso top das galáxias! Faça agora!",
                new String[]{
                        "½ litro de leite ",
                        "1 caixinha de creme de leite",
                        "1 caixinha de leite condensado",
                        "1 vidro de leite de coco (200 ml)",
                        "2 envelopes de gelatina incolor sem sabor ",
                        "10 colheres(sopa) de água",
                        "100 gramas de coco ralado em flocos para decorar",
                        "Leite condensado a gosto para decorar"
                },
                new String[]{
                        "Uma tigela hidrate a gelatina na água e dissolva conforme instruções da embalagem.",
                        "No liquidificador coloque o leite, o leite condensado, o leite de coco e o creme de leite.",
                        "Bata muito bem por cerca de 2 minutos. Adicione a gelatina dissolvida, e bata um pouco mais para incorporar.",
                        "Despeje essa mistura numa forma (22 cm de diâmetro) de furo central, levemente untada com óleo.",
                        "Leve para gelar por no mínimo 4 horas. Retire da geladeira, desenforme regue com leite condensado e polvilhe coco ralado em flocos.",
                        "E agora é só servir essa maravilha de pudim de coco delicioso!"
                }
        ));

        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.bolo_chocolate_trufado),
                "Bolo de chocolate trufado uma receita simples e prática com um resultado final maravilhoso",
                "O bolo de chocolate trufado além de ser uma paixão nacional, especialmente dos chocólatras de plantão, é extremamente delicioso. Confira o passo a passo dessa versão, onde o bolo de chocolate trufado fácil leva uma mousse de recheio irresistível! Faça hoje mesmo e apaixone-se!",
                new String[]{
                        "2 xícaras(chá) de farinha de trigo",
                        "1 ½ xícara(chá) de açúcar",
                        "1 xícara(chá) de chocolate 50% cacau",
                        "1 xícara(chá) de água morna",
                        "½ xícara(chá) de óleo",
                        "3 ovos",
                        "½ colher(chá) de bicarbonato de sódio",
                        "1 ½ colher(chá) de fermento em pó",
                        "3 colheres(sopa) de chocolate 50% cacau",
                        "½ caixinha de creme de leite",
                        "1 caixinha de leite condensado",
                        "1 colher(sopa) de manteiga ou margarina",
                        "50 gramas de chocolate nobre ½ amargo picado",
                        "150 gramas de chocolate nobre ½ amargo picado",
                        "3 colheres(sopa) de chocolate 50% cacau",
                        "300 ml de mistura para chantilly",
                        "½ caixinha de creme de leite",
                        "½ envelope de gelatina incolor sem sabor",
                        "3 colheres(sopa) rasas de água"
                },
                new String[]{
                        "Numa tigela coloque os ovos, o açúcar e bata muito bem com um fouet até obter um creme esbranquiçado, adicione o óleo e bata um pouco mais até ficar homogêneo.",
                        "Acrescente a farinha de trigo e o chocolate previamente peneirados, intercalando com a água morna, e batendo a cada adição.",
                        "Por último coloque o fermento e o bicarbonato, e mexa delicadamente com uma colher.",
                        "Despeje numa forma (20 cm de diâmetro x 10 cm de altura) untada e polvilhada com cacau em pó.",
                        "Leve para assar em forno pré-aquecido a 180 graus por cerca de 45 minutos.",
                        "Retire do forno, deixe esfriar e desenforme. Reserve.",
                        "Numa panela coloque o leite condensado, o creme de leite, a manteiga e o chocolate em pó. Leve ao fogo baixo, mexendo sempre, até começar a ferver, deixe por mais 1 minuto e retire do fogo.",
                        "Acrescente o chocolate picado e misture bem para incorporar. Transfira para uma tigela, cubra com plástico filme em contato com o creme e deixe esfriar.",
                        "Na batedeira coloque a mistura para chantilly, o chocolate em pó e bata até dobrar de volume e atingir o ponto de picos firmes.",
                        "Numa tigela coloque o chocolate e derreta em banho maria ou no microondas de 30 em 30 segundos. ",
                        "Adicione o creme de leite e misture bem para obter um creme brilhante. Adicione a gelatina hidratada e dissolvida conforme instruções da embalagem.",
                        "Junte essa ganache a chantilly de chocolate e mexa delicadamente para incorporar.",
                        "Reserve. ",
                        "Retire o topo do bolo com o auxílio de uma faca, e faça o contorno para remover o miolo, mas deixe as paredes do bolo com uma espessura de 2 cm no mínimo.",
                        "Dica: Retire o topo do bolo perfeitamente, pois ele será utilizado como uma tampa para fechar o bolo no final.",
                        "Despeje a mousse dentro da cova onde foi retirado o miolo do bolo, deixe um espaço antes da borda, e feche com o topo do bolo.",
                        "Cubra com a cobertura e espalhe bem para ficar bem uniforme. Decore com granulado de chocolate a gosto. ",
                        "E agora é só saborear esse incrível bolo de chocolate trufado fácil!"
                }
        ));

        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.fricasse_de_peru_sobras),
                "Fricassê de peru: a receita ideal para aproveitar sobras da ceia",
                "Um delicioso prato ótimo a época do final do ano é o fricassê de peru. Afinal, o peru é um ingrediente típico desse período e, dependendo da quantidade feita, pode acabar sobrando para os dias seguintes, principalmente se a sua família não for tão grande.",
                new String[]{
                        "400gr de peru cozido e desfiado",
                        "2 colheres (sopa) de azeite",
                        "2 dentes de alho amassados",
                        "1 cebola pequena picada",
                        "1 tomate sem semente picado",
                        "1/2 cenoura ralada",
                        "Sal, pimenta, cheiro verde a gosto",
                        "1 lata de milho sem água",
                        "1 caixa creme de leite (200gr)",
                        "100gr de requeijão cremoso",
                        "200 gramas de muçarela",
                        "200 gramas de batata palha"
                },
                new String[]{
                        "Coloque em uma panela, azeite, alho, cebola, refogue rapidamente, acrescente o peru desfiado, pimenta a gosto, tomate picado e cenoura, refogue bem.",
                        "Acrescente o milho, creme de leite e requeijão mexendo bem para engrossar, acerte o sal e cheiro verde a gosto, coloque em um refratário, cubra com muçarela e leve ao forno para gratinar por 10 minutos a 200 °C.",
                        "Cubra com a batata palha e sirva com arroz branco e salada de folhas.",
                        "Aproveite!"
                }
        ));

        recipeList.add(new Recipe(BitmapFactory.decodeResource(resources,R.raw.pao_de_mandioca_sem_farinha),
                "Pão de mandioca sem farinha bem fofinho e macio perfeito para seu café ou lanche",
                "Se você gosta muito de pães, mas não como por ser alérgico a glúten ou tem alguém em casa que seja, experimente fazer essa super receita de pão de mandioca sem farinha de trigo. O pão fica muito fofinho, além de muito saboroso. Você não pode deixar de fazer e experimentar essa delícia de pão de mandioca sem farinha! ",
                new String[]{
                        "400g de mandioca cozida",
                        "1 ovo",
                        "1 colher de sopa de açúcar ",
                        "1 colher de café de sal ",
                        "10g de fermento biológico seco",
                        "50mL de leite ",
                        "3 xícaras de fécula de batata (pode substituir por farinha de trigo ou farinha de arroz)",
                        "2 colheres de sopa de óleo",
                        "1 ovo (para pincelar)",
                        "semente de chia a gosto"
                },
                new String[]{
                        "No liquidificador, bata a mandioca, o ovo, o açúcar, o sal, o fermento e o leite até obter um creme liso homogêneo.",
                        "Despeje em uma tigela e adicione a fécula de batata aos poucos.",
                        "Adicione também o óleo e amasse até que a massa fique lisa e homogênea.",
                        "Cubra com plástico filme e deixe descansar por 30 minutos.",
                        "Porcione a massa bolas do tamanho da palma de uma mão.",
                        "Coloque as bolas uma ao lado da outra em uma forma de pão ou uma forma de furo, ambas untadas.",
                        "Pincele o ovo sobre os pães e polvilhe semente de chia a gosto.",
                        "Asse em forno médio, preaquecido, até dourar.",
                        "Sirva e se delicie com essa delicioso pão de mandioca sem farinha!"
                }
        ));

        return recipeList;
    }

    public Recipe getRecipeToIndex(int index){

        return getAllRecipes().get(index);

    }

}
