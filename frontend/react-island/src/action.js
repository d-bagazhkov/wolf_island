import * as ActionTypes from './constant'

export const updateConfig = (value) => ({
    type: ActionTypes.UPDATE_CONFIG,
    value
});
export const updateIsland = (value) => ({
    type: ActionTypes.UPDATE_ISLAND,
    value
});
export const updateStats = (value) => ({
    type: ActionTypes.UPDATE_STATS,
    value
});
export const updateConfigInterval = (value) => ({
    type: ActionTypes.UPDATE_CONFIG_RANGE_INTERVAL,
    value
});
export const updateConfigTypeRequest = (value) => ({
    type: ActionTypes.UPDATE_CONFIG_TYPE_REQUEST,
    value
});
export const updateConfigCellSize = (value) => ({
    type: ActionTypes.UPDATE_CONFIG_CELL_SIZE,
    value
});
export const updateConfigCountRow = (value) => ({
    type: ActionTypes.UPDATE_CONFIG_COUNT_ROW,
    value
});
export const updateConfigCountColumn = (value) => ({
    type: ActionTypes.UPDATE_CONFIG_COUNT_COLUMN,
    value
});