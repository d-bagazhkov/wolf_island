import React from 'react';
import {connect} from "react-redux";
import * as Actions from '../../action';
import InputRange from "../input-range";
import AnimaButton from "../anima-button";
import ToggleSwitch from "../toggle-switch";
import AnimaInput from "../anima-input";
import {bindActionCreators} from "redux";
import './ConfigPanel.css'

const ConfigPanel = (props) => {
    return (
        <div className="ConfigPanel">
            <InputRange onChange={props.updateConfigInterval}/>
            <ToggleSwitch onChange={props.updateConfigTypeRequest} offValue="Polling" onValue="Socket"/>
            <AnimaInput value={props.config.cellSize} update={props.updateConfigCellSize} label="Cell size"/>
            <AnimaInput value={props.config.countRow} update={props.updateConfigCountRow} label="Count rows"/>
            <AnimaInput value={props.config.countColumn} update={props.updateConfigCountColumn} label="Count columns"/>
            <AnimaButton value={"Reset"}/>
            <AnimaButton value={"Update"}/>
        </div>
    );
};

const mapStateToProps = (state) => ({
    config: state.config
});

const mapDispatchToProps = dispatch => (bindActionCreators({
    // updateConfig: Actions.updateConfig,
    updateConfigInterval: Actions.updateConfigInterval,
    updateConfigCellSize: Actions.updateConfigCellSize,
    updateConfigCountRow: Actions.updateConfigCountRow,
    updateConfigCountColumn: Actions.updateConfigCountColumn,
    updateConfigTypeRequest: Actions.updateConfigTypeRequest,
}, dispatch));
export default connect(mapStateToProps, mapDispatchToProps)(ConfigPanel);