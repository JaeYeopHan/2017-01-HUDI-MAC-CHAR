echo ------------
echo   T E S T
echo ------------

cd WEB-MAC-CHAR
npm install
npm install -g webpack
npm run dev

./gradlew check
