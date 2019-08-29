'use strict';

//todo здесь есть код)

function getRandomInt(min, max) {                           //
  return Math.floor(Math.random() * (max - min)) + min;  // заглушка
}                                                           //

const rootTable = new RootTable(elementRootTable);

const receiveQuery = () => {
  const TEST_JSON = [];             // заглушка
  for (let i = 0; i < 25; i++) {    // заглушка
    for (let j = 0; j < 10; j++) {  // заглушка
      TEST_JSON.push({              // заглушка
        x: i, y: j, value: "X"      // заглушка
      })                            // заглушка
    }                               // заглушка
  }                                 // заглушка
  return TEST_JSON;                 // заглушка
};


let intervalId;

switchToSocket = () => {
  console.log("Смена режима работы программы на Socket")
};

switchToPolling = () => {
  console.log("Смена режима работы программы на Socket")
};

startQuerying = (value) => {
  console.log("Запросы включенны с частотой", value, "ms");
  clearInterval(intervalId);
  intervalId = setInterval(resetApp, value);//загушка
};

stopQuerying = () => {
  console.log("Запросы отключенны");
  clearInterval(intervalId);
};

resetApp = () => {
  console.log("Reset app");
  //заглушка
  let arr = receiveQuery();
  for (let i = 0; i < 10; i++) {
    arr[getRandomInt(0, arr.length)].value = "0";
  }
  rootTable.render(arr);
};

rootTable.render(receiveQuery());
