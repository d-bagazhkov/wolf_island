import * as ActionTypes from './constant';

const INIT_CONFIG = {
    interval: 0,
    typeRequest: false,
    cellSize: "3rem",
    countRow: 10,
    countColumn: 10
};

const INIT_STORE = {
    island: {},
    floatConfig: INIT_CONFIG,
    config: INIT_CONFIG,
    stats: {}
};

const rootReducer = (store = INIT_STORE, action) => {
    let nextConfig;
    console.log(store, action);
    switch (action.type) {
        case ActionTypes.UPDATE_ISLAND:
            let table = {};
            action.value.forEach(cell => {
                if (Object.keys(table).includes("" + cell.row)) {
                    table["" + cell.row]["" + cell.column] = cell.content || " ";
                } else {
                    table["" + cell.row] = {};
                    table["" + cell.row]["" + cell.column] = cell.content || " ";
                }
            });
            return {...store, island: table};
        case ActionTypes.UPDATE_STATS:
            return {...store, stats: action.value};
        case ActionTypes.UPDATE_CONFIG:
            return {...store, config: store.floatConfig};
        case ActionTypes.UPDATE_CONFIG_RANGE_INTERVAL:
            nextConfig = {...store.floatConfig, interval: action.value};
            return {...store, floatConfig: nextConfig};
        case ActionTypes.UPDATE_CONFIG_TYPE_REQUEST:
            nextConfig = {...store.floatConfig, typeRequest: action.value};
            return {...store, floatConfig: nextConfig};
        case ActionTypes.UPDATE_CONFIG_CELL_SIZE:
            nextConfig = {...store.floatConfig, cellSize: action.value};
            return {...store, floatConfig: nextConfig};
        case ActionTypes.UPDATE_CONFIG_COUNT_ROW:
            nextConfig = {...store.floatConfig, countRow: action.value};
            return {...store, floatConfig: nextConfig};
        case ActionTypes.UPDATE_CONFIG_COUNT_COLUMN:
            nextConfig = {...store.floatConfig, countColumn: action.value};
            return {...store, floatConfig: nextConfig};
        default:
            console.log("Call default reducer case");
            return store;
    }
};
export default rootReducer;