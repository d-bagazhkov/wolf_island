import React from 'react';
import * as Actions from '../../action'
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

let i = 0;
let interval = [];
const Requester = (props) => {
    console.log(props);
    interval.forEach(i => clearInterval(i));
    if (props.interval)
        interval.push(
            setInterval(() => fetch(
                "http://localhost:8008/handle",
                {
                    method: 'POST',
                    headers: new Headers({
                        'Content-Type': 'application/json'
                    })
                })
                .then(response => response.json())
                .then(j => props.updateIsland(j.map(e => ({
                    row: e.position.x,
                    column: e.position.y,
                    content: e.thing.value
                })))), props.interval));
    interval.push(setInterval(() => {
        props.updateStats({
            "Count wolf": 2,
            "Count rabbit": 1,
            "Summary count steps": ++i
        })
    }, 1000));//todo remove dumb update stats

    return <div style={{display: 'none'}}/>
};

const mapStateToProps = state => ({
    interval: state.config.interval
});
const mapDispatchToProps = dispatch => bindActionCreators(
    {
        updateStats: Actions.updateStats,
        updateIsland: Actions.updateIsland,
        updateConfig: Actions.updateConfig,
        updateConfigCellSize: Actions.updateConfigCellSize,
        updateConfigCountRow: Actions.updateConfigCountRow,
        updateConfigCountColumn: Actions.updateConfigCountColumn,
        updateConfigTypeRequest: Actions.updateConfigTypeRequest,
        updateConfigInterval: Actions.updateConfigInterval,
    },
    dispatch);
export default connect(mapStateToProps, mapDispatchToProps)(Requester);