# Quiz lesson4

## 1. Аннотации:
Аннотация — это метка, которая предоставляет дополнительную информацию о классе или методе (Примечание переводчика: в java аннотации могут применяться не только для классов и методов, но также и для других элементов). Для аннотаций используется префикс «@». TestNG использует аннотации, чтобы помочь в создании надёжной структуры тестов. Давайте посмотрим на аннотации TestNG, используемые для автоматизации тестирования с Selenium.
### @Test
Это самая важная аннотация в TestNG, в которой находится основная логика теста. Все автоматизируемые функции находятся в методе с аннотацией @Test. Она имеет различные атрибуты, с помощью которых может быть настроен запуск метода.
Пример кода ниже, проверяет переход по url:
       
        @Test
        public void testCurrentUrl() throws InterruptedException {
           driver.findElement(By.xpath("//*[@id='app']/header/aside/ul/li[4]/a"))
             .click();
           String currentUrl = driver.getCurrentUrl();
           assertEquals(
             currentUrl,
             "https://automation.lambdatest.com/timeline/?viewType=build&page=1", 
             "url did not matched");
        }
### @BeforeTest
Метод с этой аннотацией запускается перед запуском первого метода с аннотацией @Test. (Примечание переводчика: в рамках теста, определенного в разделе test в xml-файле конфигурации). Вы можете использовать эту аннотацию в TestNG с Selenium для настройки браузера. Например, запустить браузер и развернуть его на весь экран, установить специфичные настройки браузера и т.д.

Ниже приведён пример для BeforeTest, в котором браузер разворачивается на весь экран:

        @BeforeTest
        public void profileSetup() {
           driver.manage().window().maximize();
        }
### @AfterTest
Методы, помеченные этой аннотацией, запускаются после всех @Test-методов вашего теста. (Примечание переводчика: в рамках теста, определенного в разделе test в xml-файле конфигурации, в оригинале не совсем корректно написано “текущего класса”). Это полезная аннотация, которая пригодится для предоставления результатов выполнения тестов. Вы можете использовать эту аннотацию, чтобы создать отчёт о ваших тестах и отправить его заинтересованным сторонам по электронной почте.

Пример кода:

        @AfterTest
        public void reportReady() {
           System.out.println("Report is ready to be shared, with screenshots of tests");
        }
### @BeforeMethod
Методы с этой аннотацией запускаются перед каждым @Test-методом. Вы можете использовать её, чтобы перед выполнением теста проверить соединение с базой данных. Или, например, при тестировании функциональности, зависимой от логина пользователя, поместить сюда код для входа в систему.
Ниже приведён фрагмент кода, демонстрирующий вход в LambdaTest:

        @BeforeMethod
        public void checkLogin() {
           driver.get("https://accounts.lambdatest.com/login");
           driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sadhvisingh24@gmail.com");
           driver.findElement(By.xpath("//input[@name='password']")).sendKeys("activa9049");
           driver.findElement(By.xpath("//*[@id='app']/section/form/div/div/button")).click();
        }
### @AfterMethod
Методы с этой аннотацией запускаются после каждого @Test-метода. Эту аннотацию можно использовать для создания скриншотов при каждом выполнении теста.
Ниже приведён фрагмент кода, демонстрирующий получение скриншота:

        public void screenShot() throws IOException {
        TakesScreenshot scr = ((TakesScreenshot) driver);
        File file1 = scr.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file1, new File("С:\\test-output\\test1.PNG"));
        }
### @BeforeClass
Метод с этой аннотацией выполнится перед первым тестовым методом в текущем классе. Эту аннотацию можно использовать для настройки свойств браузера, инициализации драйвера, открытия браузера с нужным URL-адресом и т.д.

Пример кода для BeforeClass:

        @BeforeClass
        public void appSetup() {
           driver.get(url);
        }
### @AfterClass
Метод с этой аннотацией выполнится после последнего тестового метода в текущем классе. Эта аннотация в TestNG может использоваться для выполнения действий по очистке ресурсов после выполнения теста, таких как закрытие драйвера и т.п.
Ниже приведён пример фрагмента кода, показывающего закрытие драйвера:

        @AfterClass
        public void closeUp() {
           driver.close();
        }
### @BeforeSuite
Набор тестов (suite) может состоять из нескольких классов, эта аннотация запускается перед всеми тестовыми методами всех классов. Эта аннотация помечает точку входа при запуске. Аннотацию @BeforeSuite в TestNG можно использовать для выполнения общих функций, таких как настройка и запуск Selenium или удалённых веб-драйверов и т.д.
Пример аннотации @BeforeSuite в TestNG, демонстрирующий настройку драйвера:

        @BeforeSuite
        public void setUp() {
           System.setProperty(
                   "webdriver.chrome.driver",
                   "С:\\selenium\\chromedriver.exe");
           driver = new ChromeDriver();
        }
### @AfterSuite
Эта аннотация в TestNG запускается после запуска всех методов тестирования во всех классах. Эта аннотация может использоваться для очистки перед завершением тестов, когда у вас используется несколько классов, например, закрытие драйверов и т. д.
Ниже приведён фрагмент кода для аннотации @AfterSuite в TestNG для Selenium:

        @AfterSuite
        public void cleanUp() {
           System.out.println("All close up activities completed");
        }
### @BeforeGroups
TestNG может объединять тесты в группы с помощью атрибута group в аннотации @Test. Например, если вы хотите, чтобы все схожие функции, связанные с управлением пользователями, были объединены вместе, вы можете пометить тесты, такие как dashboard (панель пользователя), profile (профиль), transactions (транзакции) и тому подобное, в одну группу, такую как “user_management”. Аннотация @BeforeGroups в TestNG помогает запустить определённые действия перед указанной группой тестов. Эту аннотацию можно использовать, если группа фокусируется на одной функциональности, как указано в приведённом выше примере. Аннотация @BeforeGroup может содержать функцию входа в систему, которая требуется для запуска тестов в группе, например, тестирование панели пользователя, профиля пользователя и т.д.

Пример использования @BeforeGroups:

        @BeforeGroups("urlValidation")
        public void setUpSecurity() {
           System.out.println("url validation test starting");
        }
### @AfterGroups
Эта аннотация запускается после выполнения всех тестовых методов указанной группы.

Пример кода для аннотации @AfterGroups в TestNG для Selenium:

        @AfterGroups("urlValidation")
        public void tearDownSecurity() {
           System.out.println("url validation test finished");
        }
### @DataProvider
Метод с этой аннотацией используется для предоставления данных тестовому методу, в котором задан атрибут dataProvider. Этот метод помогает в создании тестов, управляемых данными, в которые может передаваться несколько наборов входных значений. Метод должен возвращать двумерный массив или объект.

У аннотации @DataProvider есть два атрибута:

name — этот атрибут используется для указания имени поставщика данных. Если не указано, то по умолчанию используется название метода.
parallel — этот атрибут позволяет запускать тесты параллельно с разными данными. Наличие этого атрибута является одним из преимуществ TestNG перед Junit. По умолчанию false.

Пример ниже показывает использование аннотации @DataProvider с заданными атрибутами name и parallel.

        @DataProvider(name = "SetEnvironment", parallel = true)
        public Object[][] getData() {
           Object[][] browserProperty = new Object[][]{
                   {Platform.WIN8, "chrome", "70.0"},
                   {Platform.WIN8, "chrome", "71.0"}
           };
        
           return browserProperty;
        }	
### @Factory
Эта аннотация помогает запускать несколько тестовых классов через один тестовый класс. Проще говоря, она определяет и создаёт тесты динамически.

Приведённый ниже фрагмент кода показывает использование аннотации @Factory, которая помогает вызывать методы тестового класса.

        import org.testng.annotations.Test;
        import org.testng.annotations.Factory;
        
        class FactorySimplyTest1 {
           @Test
           public void testMethod1() {
               System.out.println("This is to test for method 1 for Factor Annotation");
           }
        }
        
        class FactorySimpleTest2 {
           @Test
           public void testMethod2() {
               System.out.println("This is to test for method 2 for Factor Annotation");
           }
        }
        
        public class FactoryAnnotation {
           @Factory()
           @Test
           public Object[] getTestFactoryMethod() {
               Object[] factoryTest = new Object[2];
               factoryTest[0] = new FactorySimplyTest1();
               factoryTest[1] = new FactorySimpleTest2();
               return factoryTest;
           }
        }
### @Parameters
Эта аннотация позволяет вам передавать параметры в ваши тесты через файл TestNG.xml. Это удобно, когда нужно передать ограниченное количество данных в ваши тесты. В случае сложных и больших наборов данных лучше использовать аннотацию @DataProvider или Excel.

Пример использования:

        @Parameters({"username", "password"})
        @Test()
        public void checkLogin(String username, String password) {
           driver.get("https://accounts.lambdatest.com/login");
           driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
           driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
           driver.findElement(By.xpath("//*[@id='app']/section/form/div/div/button")).click();
           System.out.println("The login process on lamdatest is completed");
        }
В файле TestNG.xml параметры определяются следующим образом:

        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
        <suite name="Suite">
           <test thread-count="5" name="Annotations">
        
               <parameter name="username"  value="sadhvisingh24@gmail.com" />
               <parameter name="password"  value="XXXXX" />
        
               <classes>
                   <class name="Parameter_annotation"/>
               </classes>
        
           </test> <!-- Annotations -->
        </suite> <!-- Suite -->
### @Listener
Эта аннотация помогает при логировании и подготовке отчётов. Есть несколько “слушателей” (Listener):
- IExecutionListener
- IAnnotationTransformer
- ISuiteListener
- ITestListener

## 2. Атрибуты, используемые с аннотациями в TestNG
У аннотаций в TestNG есть атрибуты, которые можно использовать для настройки. Они помогают настроить порядок выполнения тестовых методов.

Вот эти атрибуты:

1. description: можно указать описание тестового метода.
Например, @Test(description=”этот тест проверяет вход в систему”).
2. alwaysRun: этот атрибут гарантирует, что тестовый метод будет выполнен всегда, даже в случае падения тестов, от которых он зависит. Когда значение атрибута true, этот метод будет запускаться всегда.
Например, @Test(alwaysRun= true).
3. dataProvider: задаёт имя поставщика данных (data provider) для тестового метода. Предположим, что вы собираетесь запускать свои тесты в нескольких браузерах, тогда в тестовом методе с атрибутом dataProvider, можно добавить параметры для браузера и его версии, которые будут передаваться в метод поставщиком данных. В этом случае тест, содержащий этот атрибут, будет использовать эти входные данные для запуска тестов в нескольких браузерах.
Например, @Test(dataProvider=”cross-browser-testing”).
4. dependsOnMethods: предоставляет информацию о порядке выполнения тестов. Тест с этим атрибутом будет выполнен, только если успешно выполниться тест, от которого он зависит. Если тест, от которого зависит метод, падает, то тест не запускается.
Например, @Test (depenOnmethod = “login”).
5. groups: помогает сгруппировать ваши тестовые методы, ориентированные на одну функциональность, в одну группу.
Например, @Test(groups=”Payment_Module”).
Этот атрибут также позволяет управлять тем, какие тесты запускать. При запуске тестов можно игнорировать какие-то группы или, наоборот, запустить только некоторые группы. Всё, что нужно сделать, это указать нужные группы в файле TestNG.xml. В теге include указать группы, которые необходимо запустить, а в теге exclude, которые надо игнорировать.
6. dependsOnGroups: выполняет функции двух, вышеупомянутых атрибутов, то есть определяет зависимость тестового метода от указанной группы. Этот тестовый метод будет запущен только после того, как указанная группа тестов будет выполнена.
Например, @Test (depenOnMethods = «Payment_Module»).
7. priority: помогает нам определить приоритет тестовых методов. Когда TestNG выполняет тестовые методы, он может делать это в произвольном порядке. В сценарии, где вы хотите, чтобы ваши тесты выполнялись в нужном порядке, вы можете использовать атрибут priority. Приоритет по умолчанию для всех тестовых методов равен 0. Сначала выполняются тесты с меньшим значением priority.
Например, @Test (priority = 1), @Test (priority = 2). В этом случае сначала будет выполнен тест с приоритетом, равным единице, а потом тест с приоритетом два.
8. enabled: этот атрибут используется, когда вам нужно игнорировать и не запускать определённый тест. Всё, что вам нужно сделать, это установить его в false.
Например, @Test(enabled= false).
9. timeout: определяет время, за которое должен выполниться тест. Если выполнение теста превышает время, определённое атрибутом, то тест завершится с ошибкой с выбросом исключения org.testng.internal.thread.ThreadTimeoutException
Например, @Test(timeOut= 500). Обратите внимание, что время указывается в миллисекундах.
10. invocationCount: работает точно так же, как цикл. Тест будет запущен столько раз, сколько указано в invocationCount.
Например, @Test(invocationCount = 5), будет запущен 5 раз.
11. invocationTimeOut: используется вместе с вышеуказанным атрибутом invocationCount. Значение этого атрибута вместе с invocationCount указывает на то, что тест будет запущен столько раз, сколько указано в invocationCount, и в течение времени, указанного в атрибуте invocationTimeOut.
Например, @Test(invocationCount = 5, invocationTimeOut = 20).
12. expectedExceptions: помогает обрабатывать исключения, выброс которых ожидается в тестовом методе. Если исключение, указанное в атрибуте, выброшено тестовым методом, то тест прошёл успешно. В противном случае, отсутствие исключения или выброс другого исключения, не указанного в атрибуте, провалит тест.
Например, @Test(expectedExceptions = {ArithmeticException.class }).
13. successPercentage — количество успешных выполнений, ожидаемое от этого метода
14. dataProviderClass — класс, в котором TestNG будет искать поставщика данных (провайдера). Если dataProviderClass не прописан, будет искать в классе этого метода, или в его базовых классах. Если dataProviderClass указывается, метод должен быть статическим в указанном классе.
15. singleThreaded — если установлен в состояние true, все методы этого тестового класса будут выполняться в одном потоке, даже если тесты уж выполняются с атрибутом parallel=”methods”. Атрибут singleThreaded применяется только на уровне класса, и будет проигнорирован, если применен на уровне метода.
16. threadPoolSize — размер пула потоков данного метода. Метод вызывается из нескольких потоков, invocationCount раз. Если invocationCount не прописан, threadPoolSize будет проигнорен.
# 3.Практикум
## Конфигурации
При создании тест-кейсов часто нужно изменять конфигурацию или запустить с другими параметрами инициализации, и/или очистить после выполнения:

        @BeforeClass
        public void setup() {
            number = 12;
        }
        @AfterClass
        public void tearDown() {
            number = 0;
        }
setup(), с аннотацией @BeforeClass, запрашивается до запуска других методов в классе; также и tearDown()-метод вызывается после запуска других методов в классе.

Таким же образом работают аннотации @BeforeMethod, @AfterMethod, @Before/AfterGroup, @Before/AfterTest и @Before/AfterSuite в любых конфигурациях на этих уровнях.
## Выполнение
Можно запустить тест-кейс командой “test” в Maven, она затронет кейсы, аннотированные как @Test, и перенесет в «дефолтный» сьют. Можно запустить кейсы через XML-конфигурацию, пользуясь плагином:

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version>
            <configuration>
                <suiteXmlFiles>
                    <suiteXmlFile>
                       src\test\resources\test_suite.xml
                    </suiteXmlFile>
                </suiteXmlFiles>
            </configuration>
        </plugin>
Когда имеется несколько XML, покрывающих все кейсы, перечисляем в suiteXmlFiles:

        <suiteXmlFiles>
            <suiteXmlFile>
              src/test/resources/parametrized_test.xml
            </suiteXmlFile>
            <suiteXmlFile>
              src/test/resources/registration_test.xml
            </suiteXmlFile>
        </suiteXmlFiles>
Для запуска отдельного теста надо прописать библиотеку в classpath, и указать компилированный класс вместе с конфигурацией:

java org.testng.TestNG test_suite.xml
## Группы
Удобно группировать тесты, например из массы тест-кейсов выделить часть и запустить отдельно. Так и делаем, через XML-файл:

        <suite name="suite">
            <test name="test suite">
                <classes>
                    <class name="com.baeldung.RegistrationTest" />
                    <class name="com.baeldung.SignInTest" />
                </classes>
            </test>
        </suite>
Здесь учтем, что классы RegistrationTest и SignInTest теперь входят в тот же сьют, и если он запускается, кейсы в классе выполнятся.

Кроме сьютов, можно группировать «по методу». Добавляем groups в @Test:

        @Test(groups = "regression")
        public void givenNegativeNumber_sumLessthanZero_thenCorrect() {
            int sum = numbers.stream().reduce(0, Integer::sum);
         
            assertTrue(sum < 0);
        }
Запускаем группы (через XML):

        <test name="test groups">
            <groups>
                <run>
                    <include name="regression" />
                </run>
            </groups>
            <classes>
                <class
                  name="com.baeldung.SummationServiceTest" />
            </classes>
        </test>
В SummationServiceTest выполнятся методы с тегом regression.
## Параметризация
Параметризация предназначена для запуска теста с разными данными (параметрами). Например, создается отдельный метод для приема данных из другого файла. Тестовый метод становится реюзабельным, может запускаться с разными подборками данных. Применяются аннотации @Parameter и/или @DataProvider. Аннотируем метод при помощи @Parameter:

        @Test
        @Parameters({"value", "isEven"})
        public void
          givenNumberFromXML_ifEvenCheckOK_thenCorrect(int value, boolean isEven) {
            
            assertEquals(isEven, value % 2 == 0);
        }
И подаем данные через XML:

        <suite name="My test suite">
            <test name="numbersXML">
                <parameter name="value" value="1"/>
                <parameter name="isEven" value="false"/>
                <classes>
                    <class name="baeldung.com.ParametrizedTests"/>
                </classes>
            </test>
        </suite>
Так делать удобно, но иногда имеем дело со сложными структурами. Тогда работаем через @DataProvider. Например с примитивами:

        @DataProvider(name = "numbers")
        public static Object[][] evenNumbers() {
            return new Object[][]{{1, false}, {2, true}, {4, true}};
        }
         
        @Test(dataProvider = "numbers")
        public void 
          givenNumberFromDataProvider_ifEvenCheckOK_thenCorrect(Integer number, boolean expected) {    
            assertEquals(expected, number % 2 == 0);
        }
Объекты @DataProvider: 

        @Test(dataProvider = "numbersObject")
        public void 
          givenNumberObjectFromDataProvider_ifEvenCheckOK_thenCorrect(EvenNumber number) {  
            assertEquals(number.isEven(), number.getValue() % 2 == 0);
        }
         
        @DataProvider(name = "numbersObject")
        public Object[][] parameterProvider() {
            return new Object[][]{{new EvenNumber(1, false)},
              {new EvenNumber(2, true)}, {new EvenNumber(4, true)}};
        }
Таким образом можно параметризировать любой объект. Этот подход полезен в тест-кейсах интеграционного тестирования.
## Пропуск тест-кейсов
Иногда бывает нужно пропустить («скипнуть») какие-то кейсы. В таком случае ставим enabled=false в @Test:

        @Test(enabled=false)
        public void givenNumbers_sumEquals_thenCorrect() { 
            int sum = numbers.stream.reduce(0, Integer::sum);
            assertEquals(6, sum);
        }
## Связанные тесты
Когда первый тест-кейс падает, а следующие должны выполняться, при этом не отделяясь как «пропущенные». В TestNG это делается добавлением параметра dependsOnMethod в @Test: 

        @Test
        public void givenEmail_ifValid_thenTrue() {
            boolean valid = email.contains("@");
         
            assertEquals(valid, true);
        }
         
        @Test(dependsOnMethods = {"givenEmail_ifValid_thenTrue"})
        public void givenValidEmail_whenLoggedIn_thenTrue() {
            LOGGER.info("Email {} valid >> logging in", email);
        }
В данном случае тест-кейс логина зависит от тест-кейса валидации email-адреса. Когда кейс валидации не прошел, кейс логина будет пропущен.
## Параллельный запуск
В TestNG есть возможность параллельного режима запуска (многопотокового). Можно настроить выполнение методов/классов/сьютов на выполнение в отдельном потоке для экономии времени.
## Классы и методы
Указываем атрибут parallel в теге suite конфигурационного XML, значение classes:

        <suite name="suite" parallel="classes" thread-count="2">
            <test name="test suite">
                <classes>
                <class name="baeldung.com.RegistrationTest" />
                    <class name="baeldung.com.SignInTest" />
                </classes>
            </test>
        </suite>
Если в XML-конфигурации имеется много test-тегов, они все будут запущены параллельно, если указано parallel = “tests”. Чтобы параллельно запустить отдельные методы, указываем parallel = “methods”.
## Методы
Запускаем код в параллельных потоках:

        public class MultiThreadedTests {
            
            @Test(threadPoolSize = 5, invocationCount = 10, timeOut = 1000)
            public void givenMethod_whenRunInThreads_thenCorrect() {
                int count = Thread.activeCount();
         
                assertTrue(count > 1);
            }
        }
Значение threadPoolSize означает, что метод запущен в n потоках. Значения invocationCount и timeOut означают, что тест будет запущен [invocationCount] раз и завершится, когда выйдет время ожидания timeOut.
