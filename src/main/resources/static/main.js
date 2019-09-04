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
let intervalStatsId = null;
let eventStatsSource = null;
startQuerying = (value) => {
  console.log("Запросы включенны с частотой", value, "ms");
  if (!toggleCheckbox.checked) {
    if (intervalId !== null) {
      clearInterval(intervalId);//stop interval request step
    }
    if (intervalStatsId !== null) {
      clearInterval(intervalStatsId);
    }
    intervalId = setInterval(request, value);
    intervalStatsId = setInterval(() => fetch("/stats")
        .then(response => response.json())
        .then(data => updateStats(data)), value);
  } else {
    if (eventSource !== null) {
      eventSource.close();
    }
    if (eventStatsSource !== null) {
      eventStatsSource.close();
    }
    eventSource = new EventSource("/socket/handle/" + value);
    eventStatsSource = new EventSource("/socket/stats/" + value);
    eventStatsSource.onmessage = function (event) {
      updateStats(JSON.parse(event.data));
    };
    eventSource.onmessage = function (event) {
      rootTable.render(Entity.of(JSON.parse(event.data)))
    };
  }
};

stopQuerying = () => {
  console.log("Запросы отключенны");
  if (intervalId !== null) {
    clearInterval(intervalId); //stop interval request step
  }
  if (intervalStatsId !== null) {
    clearInterval(intervalStatsId);
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
  fetch("/reset")
      .then(response => response.json())
      .then(response => rootTable.render(Entity.of(response)))
};

resetApp();
