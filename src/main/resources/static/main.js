'use strict';

const rootTable = new RootTable(elementRootTable);

const request = () => {
  fetch("/handle", {
    method: 'POST',
    headers: new Headers({
      'Content-Type': 'application/json'
    }),
  }).then(response => response.json())
      .then(j => rootTable.render(Entity.of(j)));
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
  intervalId = setInterval(request, value);
};

stopQuerying = () => {
  console.log("Запросы отключенны");
  clearInterval(intervalId);
};

resetApp = () => {
  console.log("Reset app");
  fetch("/reset")
      .then(response => response.json())
      .then(response => rootTable.render(Entity.of(response)))
};

resetApp();
