import React from 'react';
import * as Actions from '../../action'
import {bindActionCreators} from "redux";
import {connect} from "react-redux";


let TMP_ISLAND = [
    {column: 0, row: 0, content: "W"},
    {column: 1, row: 0, content: ""},
    {column: 2, row: 0, content: ""},
    {column: 0, row: 1, content: "R"},
    {column: 1, row: 1, content: ""},
    {column: 2, row: 1, content: ""},
    {column: 0, row: 2, content: ""},
    {column: 1, row: 2, content: "W"},
    {column: 2, row: 2, content: ""}
];
let i = 0;
const Requester = (props) => {
    props.updateIsland(TMP_ISLAND);//todo remove dumb update island
    setInterval(() => {
        props.updateStats({
            "Count wolf": 2,
            "Count rabbit": 1,
            "Summary count steps": ++i
        })
    }, 1000);//todo remove dumb update stats

    return <div style={{display: 'none'}}/>
};

const mapStateToProps = state => ({globalState: state});
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
export default connect(null, mapDispatchToProps)(Requester);