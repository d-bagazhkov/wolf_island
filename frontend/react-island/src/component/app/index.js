import React from 'react';
import {AppLayout} from "../../layaout/";
import Island from "../island/";
import {ControlLayout} from "../../layaout/";
import ConfigPanel from "../config-panel";
import StatsPanel from "../stats-panel";
import Requester from "../requester";

const App = () => (
    <AppLayout>
        <ControlLayout>
            <ConfigPanel/>
            <StatsPanel/>
        </ControlLayout>
        <Island/>
        <Requester/>
    </AppLayout>
);


export default App;
