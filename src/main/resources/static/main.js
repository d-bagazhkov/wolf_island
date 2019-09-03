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
  console.log("Смена режима работы программы на Socket")
};

let intervalId = null;
let eventSource = null;
startQuerying = (value) => {
  console.log("Запросы включенны с частотой", value, "ms");
  if (!toggleCheckbox.checked) {
    clearInterval(intervalId);
    intervalId = setInterval(request, value);
  } else {
    if (eventSource) {
      eventSource.close();
    }
    eventSource = new EventSource("/socket/handle/" + value);
    eventSource.onmessage = function(event) {
      rootTable.render(Entity.of(JSON.parse(event.data)))
    };
  }
};

stopQuerying = () => {
  console.log("Запросы отключенны");
  if (intervalId !== null)
    clearInterval(intervalId);
  if (eventSource !== null) {
    eventSource.close();
  }
};

resetApp = () => {
  console.log("Reset app");
  fetch("/reset")
      .then(response => response.json())
      .then(response => rootTable.render(Entity.of(response)))
};

resetApp();
