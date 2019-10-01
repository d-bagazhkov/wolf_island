import React from 'react';
import NoData from "../no-data";
import './StatsPanel.css';
import {connect} from "react-redux";

const StatsPanel = (props) => (
    <div className="StatsPanel">
        <ul className="statisticList">
            {
                props.stats && Object.entries(props.stats).length
                    ? Object.entries(props.stats).map((kv, index) => {
                        let [key, value] = kv;
                        return (
                            <li key={index} className="item">
                                <div className="key">{key}</div>
                                <div className="value">{value}</div>
                            </li>
                        )
                    })
                    : <NoData/>
            }
        </ul>
    </div>
);

const mapStateToProps = state => ({
    stats: state.stats
});

export default connect(mapStateToProps)(StatsPanel);