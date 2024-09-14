ExamBot


Overview:

ExamBot is a bot which can communicate with chatgpt and based on user input.
It can generate questions for a given topic for given number and difficulty level.
This set of questions is called a questionPaper.
the application should convert them to file.
if given a questionPaper application should be able to generate a answer sheet.
answer sheet should contain each question and their respective answer right below.

Requirements
1.question
2.questionPaper
3.answer
4.answersheet
5.api to connect with chatgpt and generate questionPaper based on user input.
6.turn questionPaper to file/pdf.
7.api to connect with chatgpt and generate answersheet based on questionPaper.
8.turn answersheet to file/pdf.

APIS:

questions Curl:
curl --location 'localhost:8080/getQuestionPaper' \
--header 'Content-Type: application/json' \
--data '{
"topic":"design pattern",
"questionsCount":10,
"generateFile":"PDF",
"questionLevel":"MEDIUM"
}'

answers Curl: 
curl --location 'localhost:8080/getAnswerSheet' \
--header 'Content-Type: application/json' \
--data '{
"questionPaperId":1,
"generateType":"PDF"
}'