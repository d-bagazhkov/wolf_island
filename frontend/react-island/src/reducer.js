import * as ActionTypes from './constant';

const INIT_STORE = {
    island: {},
    config: {
        interval: 0,
        typeRequest: false,
        cellSize: "3rem",
        countRow: 10,
        countColumn: 10
    },
    stats: {}
};

const rootReducer = (store = INIT_STORE, action) => {
    let nextConfig;
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
            return {...store, config: action.value};
        case ActionTypes.UPDATE_CONFIG_RANGE_INTERVAL:
            nextConfig = {...store.config, interval: action.value};
            return {...store, config: nextConfig};
        case ActionTypes.UPDATE_CONFIG_TYPE_REQUEST:
            nextConfig = {...store.config, typeRequest: action.value};
            return {...store, config: nextConfig};
        case ActionTypes.UPDATE_CONFIG_CELL_SIZE:
            nextConfig = {...store.config, cellSize: action.value};
            return {...store, config: nextConfig};
        case ActionTypes.UPDATE_CONFIG_COUNT_ROW:
            nextConfig = {...store.config, countRow: action.value};
            return {...store, config: nextConfig};
        case ActionTypes.UPDATE_CONFIG_COUNT_COLUMN:
            nextConfig = {...store.config, countColumn: action.value};
            return {...store, config: nextConfig};
        default:
            console.log("Call default reducer case");
            return store;
    }
};
export default rootReducer;