<template>
    <div>
        <v-form-render :form-json="formJson" :form-data="formData" :option-data="optionData" ref="vFormRef">
        </v-form-render>
        <el-button type="primary" @click="submitForm">Submit</el-button>
    </div>
</template>

<script setup>
import {ref, reactive} from 'vue'
import {ElMessage} from 'element-plus'

const formJson = reactive({
    "widgetList": [{
        "key": 69680,
        "type": "static-text",
        "icon": "static-text",
        "formItemFlag": false,
        "options": {
            "name": "titleText",
            "columnWidth": "200px",
            "hidden": false,
            "textContent": "活动创建",
            "textAlign": "center",
            "fontSize": "26px",
            "preWrap": false,
            "customClass": [],
            "onCreated": "",
            "onMounted": "",
            "label": "static-text"
        },
        "id": "titleText"
    }, {
        "key": 73354,
        "type": "divider",
        "icon": "divider",
        "formItemFlag": false,
        "options": {
            "name": "divider66067",
            "label": "基本信息",
            "columnWidth": "200px",
            "direction": "horizontal",
            "contentPosition": "center",
            "hidden": false,
            "customClass": [],
            "onCreated": "",
            "onMounted": ""
        },
        "id": "divider66067"
    }, {
        "key": 22568,
        "type": "grid",
        "category": "container",
        "icon": "grid",
        "cols": [{
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol39938",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-39938"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [{
                "key": 55387,
                "type": "input",
                "icon": "text-field",
                "formItemFlag": true,
                "options": {
                    "name": "activityNameInput",
                    "label": "活动名称",
                    "labelAlign": "",
                    "type": "text",
                    "defaultValue": "",
                    "placeholder": "",
                    "columnWidth": "200px",
                    "size": "",
                    "labelWidth": null,
                    "labelHidden": false,
                    "readonly": false,
                    "disabled": false,
                    "hidden": false,
                    "clearable": true,
                    "showPassword": false,
                    "required": true,
                    "requiredHint": "必填",
                    "validation": "",
                    "validationHint": "activityName",
                    "customClass": [],
                    "labelIconClass": null,
                    "labelIconPosition": "rear",
                    "labelTooltip": null,
                    "minLength": null,
                    "maxLength": null,
                    "showWordLimit": false,
                    "prefixIcon": "",
                    "suffixIcon": "",
                    "appendButton": false,
                    "appendButtonDisabled": false,
                    "buttonIcon": "custom-search",
                    "onCreated": "",
                    "onMounted": "",
                    "onInput": "",
                    "onChange": "",
                    "onFocus": "",
                    "onBlur": "",
                    "onValidate": "",
                    "onAppendButtonClick": ""
                },
                "id": "input29232"
            }],
            "options": {
                "name": "gridCol100048",
                "hidden": false,
                "span": 20,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-100048"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol73657",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-73657"
        }],
        "options": {"name": "grid18677", "hidden": false, "gutter": 12, "colHeight": null, "customClass": []},
        "id": "grid18677"
    }, {
        "type": "grid",
        "category": "container",
        "icon": "grid",
        "cols": [{
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol92040",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-92040"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [{
                "key": 48934,
                "type": "textarea",
                "icon": "textarea-field",
                "formItemFlag": true,
                "options": {
                    "name": "activityIntroInput",
                    "label": "活动介绍",
                    "labelAlign": "",
                    "rows": 3,
                    "defaultValue": "",
                    "placeholder": "",
                    "columnWidth": "200px",
                    "size": "",
                    "labelWidth": null,
                    "labelHidden": false,
                    "readonly": false,
                    "disabled": false,
                    "hidden": false,
                    "required": false,
                    "requiredHint": "",
                    "validation": "",
                    "validationHint": "",
                    "customClass": "",
                    "labelIconClass": null,
                    "labelIconPosition": "rear",
                    "labelTooltip": null,
                    "minLength": null,
                    "maxLength": null,
                    "showWordLimit": false,
                    "onCreated": "",
                    "onMounted": "",
                    "onInput": "",
                    "onChange": "",
                    "onFocus": "",
                    "onBlur": "",
                    "onValidate": ""
                },
                "id": "textarea54504"
            }],
            "options": {
                "name": "gridCol109430",
                "hidden": false,
                "span": 20,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-109430"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol84110",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-84110"
        }],
        "options": {"name": "grid102165", "hidden": false, "gutter": 12, "colHeight": null, "customClass": ""},
        "id": "grid102165"
    }, {
        "type": "grid",
        "category": "container",
        "icon": "grid",
        "cols": [{
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol17743",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-17743"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [{
                "key": 65793,
                "type": "select",
                "icon": "select-field",
                "formItemFlag": true,
                "options": {
                    "name": "activityClassSelector",
                    "label": "活动类型",
                    "labelAlign": "",
                    "defaultValue": "",
                    "placeholder": "",
                    "columnWidth": "200px",
                    "size": "",
                    "labelWidth": null,
                    "labelHidden": false,
                    "disabled": false,
                    "hidden": false,
                    "clearable": true,
                    "filterable": false,
                    "allowCreate": false,
                    "remote": false,
                    "automaticDropdown": false,
                    "multiple": false,
                    "multipleLimit": 0,
                    "optionItems": [{"label": "select 1", "value": 1}, {
                        "label": "select 2",
                        "value": 2
                    }, {"label": "select 3", "value": 3}],
                    "required": true,
                    "requiredHint": "请选择活动类型",
                    "validation": "",
                    "validationHint": "",
                    "customClass": "",
                    "labelIconClass": null,
                    "labelIconPosition": "rear",
                    "labelTooltip": null,
                    "onCreated": "",
                    "onMounted": "",
                    "onRemoteQuery": "",
                    "onChange": "",
                    "onFocus": "",
                    "onBlur": "",
                    "onValidate": ""
                },
                "id": "select99189"
            }],
            "options": {
                "name": "gridCol93550",
                "hidden": false,
                "span": 20,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-93550"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol71220",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-71220"
        }],
        "options": {"name": "grid45037", "hidden": false, "gutter": 12, "colHeight": null, "customClass": ""},
        "id": "grid45037"
    }, {
        "type": "grid",
        "category": "container",
        "icon": "grid",
        "cols": [{
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol73542",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-73542"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [{
                "key": 75934,
                "type": "time-range",
                "icon": "time-range-field",
                "formItemFlag": true,
                "options": {
                    "name": "activityTimeRangeSelector",
                    "label": "活动时间",
                    "labelAlign": "",
                    "defaultValue": null,
                    "startPlaceholder": "",
                    "endPlaceholder": "",
                    "columnWidth": "200px",
                    "size": "",
                    "autoFullWidth": true,
                    "labelWidth": null,
                    "labelHidden": false,
                    "readonly": false,
                    "disabled": false,
                    "hidden": false,
                    "clearable": true,
                    "editable": false,
                    "format": "HH:mm:ss",
                    "required": true,
                    "requiredHint": "请选择活动时间",
                    "validation": "",
                    "validationHint": "",
                    "customClass": [],
                    "labelIconClass": null,
                    "labelIconPosition": "rear",
                    "labelTooltip": null,
                    "onCreated": "",
                    "onMounted": "",
                    "onChange": "",
                    "onFocus": "",
                    "onBlur": "",
                    "onValidate": ""
                },
                "id": "timerange41762"
            }],
            "options": {
                "name": "gridCol34353",
                "hidden": false,
                "span": 20,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": []
            },
            "id": "grid-col-34353"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol67302",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-67302"
        }],
        "options": {"name": "grid28923", "hidden": false, "gutter": 12, "colHeight": null, "customClass": ""},
        "id": "grid28923"
    }, {
        "type": "grid",
        "category": "container",
        "icon": "grid",
        "cols": [{
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol19297",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-19297"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [{
                "key": 107512,
                "type": "cascader",
                "icon": "cascader-field",
                "formItemFlag": true,
                "options": {
                    "name": "activityClassroomSelector",
                    "label": "活动教室",
                    "labelAlign": "",
                    "defaultValue": "",
                    "placeholder": "选择活动教室",
                    "size": "",
                    "labelWidth": null,
                    "labelHidden": false,
                    "columnWidth": "200px",
                    "disabled": false,
                    "hidden": false,
                    "clearable": true,
                    "filterable": false,
                    "multiple": true,
                    "checkStrictly": false,
                    "showAllLevels": true,
                    "optionItems": [{
                        "label": "select 1",
                        "value": 1,
                        "children": [{"label": "child 1", "value": 11}]
                    }, {"label": "select 2", "value": 2}, {"label": "select 3", "value": 3}],
                    "required": true,
                    "requiredHint": "请选择活动教室",
                    "customRule": "",
                    "customRuleHint": "",
                    "customClass": [],
                    "labelIconClass": null,
                    "labelIconPosition": "rear",
                    "labelTooltip": null,
                    "onCreated": "",
                    "onMounted": "",
                    "onChange": "",
                    "onFocus": "",
                    "onBlur": "",
                    "onValidate": ""
                },
                "id": "cascader35430"
            }],
            "options": {
                "name": "gridCol9591",
                "hidden": false,
                "span": 20,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-9591"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol34682",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-34682"
        }],
        "options": {"name": "grid73687", "hidden": false, "gutter": 12, "colHeight": null, "customClass": []},
        "id": "grid73687"
    }, {
        "type": "grid",
        "category": "container",
        "icon": "grid",
        "cols": [{
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol17525",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-17525"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol19820",
                "hidden": false,
                "span": 20,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-19820"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol20120",
                "hidden": false,
                "span": 2,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-20120"
        }],
        "options": {"name": "grid117141", "hidden": false, "gutter": 12, "colHeight": null, "customClass": ""},
        "id": "grid117141"
    }, {
        "key": 22568,
        "type": "grid",
        "category": "container",
        "icon": "grid",
        "cols": [{
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol118214",
                "hidden": false,
                "span": 4,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": []
            },
            "id": "grid-col-118214"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [{
                "key": 95104,
                "type": "button",
                "icon": "button",
                "formItemFlag": false,
                "options": {
                    "name": "button78351",
                    "label": "返回",
                    "columnWidth": "200px",
                    "size": "",
                    "displayStyle": "block",
                    "disabled": false,
                    "hidden": false,
                    "type": "danger",
                    "plain": true,
                    "round": false,
                    "circle": false,
                    "icon": null,
                    "customClass": [],
                    "onCreated": "",
                    "onMounted": "",
                    "onClick": ""
                },
                "id": "button78351"
            }],
            "options": {
                "name": "gridCol28040",
                "hidden": false,
                "span": 4,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-28040"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol59896",
                "hidden": false,
                "span": 4,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": []
            },
            "id": "grid-col-59896"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol110534",
                "hidden": false,
                "span": 4,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": ""
            },
            "id": "grid-col-110534"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [{
                "key": 95104,
                "type": "button",
                "icon": "button",
                "formItemFlag": false,
                "options": {
                    "name": "button120953",
                    "label": "保存",
                    "columnWidth": "200px",
                    "size": "",
                    "displayStyle": "inline",
                    "disabled": false,
                    "hidden": false,
                    "type": "primary",
                    "plain": false,
                    "round": false,
                    "circle": false,
                    "icon": null,
                    "customClass": [],
                    "onCreated": "",
                    "onMounted": "",
                    "onClick": ""
                },
                "id": "button120953"
            }],
            "options": {
                "name": "gridCol63790",
                "hidden": false,
                "span": 4,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": []
            },
            "id": "grid-col-63790"
        }, {
            "type": "grid-col",
            "category": "container",
            "icon": "grid-col",
            "internal": true,
            "widgetList": [],
            "options": {
                "name": "gridCol26591",
                "hidden": false,
                "span": 4,
                "offset": 0,
                "push": 0,
                "pull": 0,
                "responsive": false,
                "md": 12,
                "sm": 12,
                "xs": 12,
                "customClass": []
            },
            "id": "grid-col-26591"
        }],
        "options": {"name": "grid39173", "hidden": false, "gutter": 12, "colHeight": null, "customClass": []},
        "id": "grid39173"
    }],
    "formConfig": {
        "modelName": "formData",
        "refName": "vForm",
        "rulesName": "rules",
        "labelWidth": 80,
        "labelPosition": "left",
        "size": "",
        "labelAlign": "label-center-align",
        "cssCode": "",
        "customClass": [],
        "functions": "",
        "layoutType": "PC",
        "jsonVersion": 3,
        "onFormCreated": "",
        "onFormMounted": "",
        "onFormDataChange": ""
    }
})
const formData = reactive({})
const optionData = reactive({})
const vFormRef = ref(null)

const submitForm = () => {
    vFormRef.value.getFormData().then(formData => {
        // Form Validation OK
        alert(JSON.stringify(formData))
    }).catch(error => {
        // Form Validation failed
        ElMessage.error(error)
    })
}
</script>