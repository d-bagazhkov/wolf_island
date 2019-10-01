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

switchToSocket = () => {
  console.log("Смена режима работы программы на Socket")
};

switchToPolling = () => {
  console.log("Смена режима работы программы на Polling")
};

let intervalId = null;
let eventSource = null;
let eventStatsSource = null;
startQuerying = (value) => {
  console.log("Запросы включенны с частотой", value, "ms");
  if (!toggleCheckbox.checked) {
    if (intervalId !== null) {
      clearInterval(intervalId);//stop interval request step
    }
    intervalId = setInterval(request, value);
  } else {
    if (eventSource !== null) {
      eventSource.close();
    }
    eventSource = new EventSource("/socket/handle/" + value);
    eventSource.onmessage = function (event) {
      rootTable.render(Entity.of(JSON.parse(event.data)))
    };
  }

  if (eventStatsSource !== null) {
    eventStatsSource.close();
  }
  eventStatsSource = new EventSource("/socket/stats/" + value);
  eventStatsSource.onmessage = function (event) {
    updateStats(JSON.parse(event.data));
  };
};

stopQuerying = () => {
  console.log("Запросы отключенны");
  if (intervalId !== null) {
    clearInterval(intervalId); //stop interval request step
  }
  if (eventSource !== null) {
    eventSource.close();
  }
  if (eventStatsSource !== null) {
    eventStatsSource.close();
  }
};

resetApp = () => {
  console.log("Reset app");
  fetch("http://localhost:8008/reset")
      .then(response => response.json())
      .then(response => rootTable.render(Entity.of(response)))
};
let TMP_ISLAND = [
  {x: 0, y: 0, value: "W"},
  {x: 1, y: 0, value: " "},
  {x: 2, y: 0, value: " "},
  {x: 0, y: 1, value: "R"},
  {x: 1, y: 1, value: " "},
  {x: 2, y: 1, value: " "},
  {x: 0, y: 2, value: " "},
  {x: 1, y: 2, value: "W"},
  {x: 2, y: 2, value: " "},
];
resetApp();
// rootTable.render(TMP_ISLAND)