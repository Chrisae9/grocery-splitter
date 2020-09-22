import React, { Fragment } from 'react'

import Form from "./Form"
import Receipts from "./Receipts"

export default function Dashboard() {
    return (
        <Fragment>
            <Form />
            <Receipts />
        </Fragment>
    )
}
