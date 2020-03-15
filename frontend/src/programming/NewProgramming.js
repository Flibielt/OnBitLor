import React, {Component} from "react";
import {Button, Form} from "antd";
import TextArea from "antd/es/input/TextArea";
import FormItem from "antd/es/form/FormItem";
import {withRouter} from "react-router-dom";

class NewProgramming extends Component{
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            description: '',
            bit: 0
        };

        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.handleBitChange = this.handleBitChange.bind(this);
    }

    handleNameChange(event) {
        if (event.target.value.length < 200) {
            this.setState({
                name: event.target.value
            })
        }
    }

    handleDescriptionChange(event) {
        if (event.target.value.length < 1000) {
            this.setState({
                description: event.target.value
            })
        }
    }

    handleBitChange(event) {
        if (event.target.value.length < 20) {
            if (!isNaN(event.target.value)) {
                this.setState({
                    bit: event.target.value
                })
            }
        }
    }

    isFormInvalid() {
        if (this.state.name.length === 0) {
            return true;
        }
        if (this.state.description === 0) {
            return true;
        }
        if (this.state.bit.length === 0) {
            return true;
        }
        if (isNaN(this.state.bit)) {
            return true;
        }
        return this.state.bit <= 0;
    }

    render() {
        return (
            <div className="container">
                <h1>
                    Add new programming competition
                </h1>
                <Form>
                    <FormItem
                        label="Name">
                        <TextArea
                            style={{resize: "none"}}
                            placeholder="Enter the competition name"
                            name="name"
                            autoSize={{minRows: 1, maxRows: 1}}
                            value={this.state.name}
                            onChange={this.handleNameChange} />
                    </FormItem>
                    <FormItem
                        label="Description">
                        <TextArea
                            style={{resize: "none"}}
                            placeholder="Enter the competition description"
                            name="description"
                            autoSize={{minRows: 8, maxRows: 8}}
                            value={this.state.description}
                            onChange={this.handleDescriptionChange} />
                    </FormItem>
                    <FormItem
                        label="Bit">
                        <TextArea
                            style={{resize: "none"}}
                            placeholder="Enter the bit award of the competition"
                            name="bit"
                            autoSize={{minRows: 1, maxRows: 1}}
                            value={this.state.bit}
                            onChange={this.handleBitChange} />
                    </FormItem>
                    <FormItem className="formItem">
                        <Button type="primary"
                                htmlType="submit"
                                disabled={this.isFormInvalid()}
                                size="large">
                            Add new competition
                        </Button>
                    </FormItem>
                </Form>
            </div>
        );
    }
}

export default withRouter(NewProgramming);
