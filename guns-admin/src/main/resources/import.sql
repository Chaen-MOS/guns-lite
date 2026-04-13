INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('16', '0', '0', 'Status', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('17', '1', '16', 'Enabled', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('18', '2', '16', 'Disabled', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('29', '0', '0', 'Gender', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('30', '1', '29', 'Male', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('31', '2', '29', 'Female', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('35', '0', '0', 'Account Status', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('36', '1', '35', 'Enabled', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('37', '2', '35', 'Frozen', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('38', '3', '35', 'Deleted', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('53', '0', '0', 'Document Type', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('54', '1', '53', 'ID Card', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('55', '2', '53', 'Passport', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('68', '0', '0', 'Yes/No', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('69', '1', '68', 'Yes', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('70', '0', '68', 'No', '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('71', '0', '0', 'Message Type', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('72', '0', '71', 'SMS', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
INSERT INTO `t_sys_dict`(id,value,pid,name,create_time,create_by,modify_time,modify_by) VALUES ('73', '1', '71', 'Email', '2019-07-23 21:30:09', '1', '2019-07-23 21:30:09', '1');
-- ----------------------------
-- Records of t_sys_cfg
-- ----------------------------
INSERT INTO `t_sys_cfg` VALUES ('1', null, null, '1', '2019-04-15 21:36:07', 'Application Name update by 2019-03-27 11:47:04', 'system.app.name', 'guns-lite');
INSERT INTO `t_sys_cfg` VALUES ('2', null, null, '1', '2019-04-15 21:36:17', 'Default system upload file path', 'system.file.upload.path', '/data/guns-lite/runtime/upload');
INSERT INTO `t_sys_cfg` VALUES ('3', null, null, '1', '2019-04-15 21:36:17', 'Tencent SMS API AppID', 'api.tencent.sms.appid', '1400219425');
INSERT INTO `t_sys_cfg` VALUES ('4', null, null, '1', '2019-04-15 21:36:17', 'Tencent SMS API AppKey', 'api.tencent.sms.appkey', '5f71ed5325f3b292946530a1773e997a');
INSERT INTO `t_sys_cfg` VALUES ('5', null, null, '1', '2019-04-15 21:36:17', 'Tencent SMS API signature parameter', 'api.tencent.sms.sign', 'Application required');

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` VALUES ('1', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Sandia Group', '1', '0', '[0],', 'Head Office', '', null);
INSERT INTO `t_sys_dept` VALUES ('2', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Development Department', '2', '1', '[0],[1],', 'Development Department', null, null);
INSERT INTO `t_sys_dept` VALUES ('3', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Operations Department', '3', '1', '[0],[1],', 'Operations Department', null, null);
INSERT INTO `t_sys_dept` VALUES ('4', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Strategy Department', '4', '1', '[0],[1],', 'Strategy Department', null, null);
INSERT INTO `t_sys_dept` VALUES ('5', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Human Resources Department', '5', '1', '[0],[1],', 'Human Resources Department', null, null);
INSERT INTO `t_sys_dept` VALUES ('6', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Administration Department', '6', '1', '[0],[1],', 'Administration Department', null, null);
INSERT INTO `t_sys_dept` VALUES ('7', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Sandia Shanghai Co., Ltd.', '7', '1', '[0],[1],', 'Shanghai Branch', '', null);
INSERT INTO `t_sys_dept` VALUES ('8', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Operations & Maintenance Department', '8', '7', '[0],[1],[7],', 'Operations & Maintenance Department', null, null);
INSERT INTO `t_sys_dept` VALUES ('9', '1', '2019-08-15 16:31:13', '1', '2019-08-15 16:31:13', 'Sales Department', '9', '7', '[0],[1],[7],', 'Sales Department', null, null);


-- ----------------------------
-- Records of t_sys_login_log
-- ----------------------------
INSERT INTO `t_sys_login_log` (`id`, `logname`, `userid`, `create_time`, `succeed`, `message`, `ip`) VALUES ('71', 'Login Log', '1', '2019-05-10 13:17:43', 'Success', null, '127.0.0.1');
INSERT INTO `t_sys_login_log` (`id`, `logname`, `userid`, `create_time`, `succeed`, `message`, `ip`) VALUES ('72', 'Login Log', '1', '2019-05-12 13:36:56', 'Success', null, '127.0.0.1');

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', null, null, null, null, 'system', 'fa-cog', '1', '1', '1', 'System Management', '1', '0', '[0],', '1', null, '/system');
INSERT INTO `t_sys_menu` VALUES ('4', null, null, '1', '2019-04-16 18:59:15', 'mgr', '', '1', null, '2', 'User Management', '1', 'system', '[0],[system],', '1', null, '/mgr');
INSERT INTO `t_sys_menu` VALUES ('5', null, null, null, null, 'mgr_add', '', '0', null, '3', 'Add User', '1', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/add');
INSERT INTO `t_sys_menu` VALUES ('6', null, null, null, null, 'mgr_edit', '', '0', null, '3', 'Edit User', '2', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/edit');
INSERT INTO `t_sys_menu` VALUES ('7', null, null, null, null, 'mgr_delete', '', '0', '0', '3', 'Delete User', '3', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/delete');
INSERT INTO `t_sys_menu` VALUES ('8', null, null, null, null, 'mgr_reset', '', '0', '0', '3', 'Reset Password', '4', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/reset');
INSERT INTO `t_sys_menu` VALUES ('9', null, null, null, null, 'mgr_freeze', '', '0', '0', '3', 'Freeze User', '5', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/freeze');
INSERT INTO `t_sys_menu` VALUES ('10', null, null, null, null, 'mgr_unfreeze', '', '0', '0', '3', 'Unfreeze User', '6', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/unfreeze');
INSERT INTO `t_sys_menu` VALUES ('11', null, null, null, null, 'mgr_setRole', '', '0', '0', '3', 'Assign Role', '7', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/setRole');
INSERT INTO `t_sys_menu` VALUES ('12', null, null, null, null, 'role', '', '1', '0', '2', 'Role Management', '2', 'system', '[0],[system],', '1', null, '/role');
INSERT INTO `t_sys_menu` VALUES ('13', null, null, null, null, 'role_add', '', '0', '0', '3', 'Add Role', '1', 'role', '[0],[system],[role],', '1', null, '/role/add');
INSERT INTO `t_sys_menu` VALUES ('14', null, null, null, null, 'role_edit', '', '0', '0', '3', 'Edit Role', '2', 'role', '[0],[system],[role],', '1', null, '/role/edit');
INSERT INTO `t_sys_menu` VALUES ('15', null, null, null, null, 'role_remove', '', '0', '0', '3', 'Delete Role', '3', 'role', '[0],[system],[role],', '1', null, '/role/remove');
INSERT INTO `t_sys_menu` VALUES ('16', null, null, null, null, 'role_setAuthority', '', '0', '0', '3', 'Configure Permissions', '4', 'role', '[0],[system],[role],', '1', null, '/role/setAuthority');
INSERT INTO `t_sys_menu` VALUES ('17', null, null, null, null, 'menu', '', '1', '0', '2', 'Menu Management', '4', 'system', '[0],[system],', '1', null, '/menu');
INSERT INTO `t_sys_menu` VALUES ('18', null, null, null, null, 'menu_add', '', '0', '0', '3', 'Add Menu', '1', 'menu', '[0],[system],[menu],', '1', null, '/menu/add');
INSERT INTO `t_sys_menu` VALUES ('19', null, null, null, null, 'menu_edit', '', '0', '0', '3', 'Edit Menu', '2', 'menu', '[0],[system],[menu],', '1', null, '/menu/edit');
INSERT INTO `t_sys_menu` VALUES ('20', null, null, null, null, 'menu_remove', '', '0', '0', '3', 'Delete Menu', '3', 'menu', '[0],[system],[menu],', '1', null, '/menu/remove');
INSERT INTO `t_sys_menu` VALUES ('58', null, null, '47', '2019-06-02 10:25:31', 'log', null, '1', null, '2', 'Business Log', '6', 'operationMgr', '[0],[operationMgr],', '1', null, '/log');
INSERT INTO `t_sys_menu` VALUES ('21', null, null, null, null, 'dept', '', '1', null, '2', 'Department Management', '3', 'system', '[0],[system],', '1', null, '/dept');
INSERT INTO `t_sys_menu` VALUES ('22', null, null, null, null, 'dict', '', '1', null, '2', 'Dictionary Management', '4', 'system', '[0],[system],', '1', null, '/dict');
INSERT INTO `t_sys_menu` VALUES ('59', null, null, '47', '2019-06-02 10:25:36', 'loginLog', null, '1', null, '2', 'Login Log', '6', 'operationMgr', '[0],[operationMgr],', '1', null, '/loginLog');
INSERT INTO `t_sys_menu` VALUES ('60', null, null, null, null, 'log_clean', '', '0', null, '3', 'Clear Logs', '3', 'log', '[0],[system],[log],', '1', null, '/log/delLog');
INSERT INTO `t_sys_menu` VALUES ('36', null, null, null, null, 'dept_add', '', '0', null, '3', 'Add Department', '1', 'dept', '[0],[system],[dept],', '1', null, '/dept/add');
INSERT INTO `t_sys_menu` VALUES ('23', null, null, null, null, 'dept_update', '', '0', null, '3', 'Edit Department', '1', 'dept', '[0],[system],[dept],', '1', null, '/dept/update');
INSERT INTO `t_sys_menu` VALUES ('24', null, null, null, null, 'dept_delete', '', '0', null, '3', 'Delete Department', '1', 'dept', '[0],[system],[dept],', '1', null, '/dept/delete');
INSERT INTO `t_sys_menu` VALUES ('25', null, null, null, null, 'dict_add', '', '0', null, '3', 'Add Dictionary', '1', 'dict', '[0],[system],[dict],', '1', null, '/dict/add');
INSERT INTO `t_sys_menu` VALUES ('26', null, null, null, null, 'dict_update', '', '0', null, '3', 'Edit Dictionary', '1', 'dict', '[0],[system],[dict],', '1', null, '/dict/update');
INSERT INTO `t_sys_menu` VALUES ('27', null, null, null, null, 'dict_delete', '', '0', null, '3', 'Delete Dictionary', '1', 'dict', '[0],[system],[dict],', '1', null, '/dict/delete');
INSERT INTO `t_sys_menu` VALUES ('28', null, null, null, null, 'to_menu_edit', '', '0', null, '3', 'Menu Edit Redirect', '4', 'menu', '[0],[system],[menu],', '1', null, '/menu/menu_edit');
INSERT INTO `t_sys_menu` VALUES ('29', null, null, null, null, 'menu_list', '', '0', null, '3', 'Menu List', '5', 'menu', '[0],[system],[menu],', '1', null, '/menu/list');
INSERT INTO `t_sys_menu` VALUES ('30', null, null, null, null, 'to_dept_update', '', '0', null, '3', 'Department Edit Redirect', '4', 'dept', '[0],[system],[dept],', '1', null, '/dept/dept_update');
INSERT INTO `t_sys_menu` VALUES ('31', null, null, null, null, 'dept_list', '', '0', null, '3', 'Department List', '5', 'dept', '[0],[system],[dept],', '1', null, '/dept/list');
INSERT INTO `t_sys_menu` VALUES ('32', null, null, null, null, 'dept_detail', '', '0', null, '3', 'Department Details', '6', 'dept', '[0],[system],[dept],', '1', null, '/dept/detail');
INSERT INTO `t_sys_menu` VALUES ('33', null, null, null, null, 'to_dict_edit', '', '0', null, '3', 'Dictionary Edit Redirect', '4', 'dict', '[0],[system],[dict],', '1', null, '/dict/dict_edit');
INSERT INTO `t_sys_menu` VALUES ('34', null, null, null, null, 'dict_list', '', '0', null, '3', 'Dictionary List', '5', 'dict', '[0],[system],[dict],', '1', null, '/dict/list');
INSERT INTO `t_sys_menu` VALUES ('35', null, null, null, null, 'dict_detail', '', '0', null, '3', 'Dictionary Details', '6', 'dict', '[0],[system],[dict],', '1', null, '/dict/detail');
INSERT INTO `t_sys_menu` VALUES ('61', null, null, null, null, 'log_detail', '', '0', null, '3', 'Log Details', '3', 'log', '[0],[system],[log],', '1', null, '/log/detail');
INSERT INTO `t_sys_menu` VALUES ('62', null, null, null, null, 'del_login_log', '', '0', null, '3', 'Clear Login Logs', '1', 'loginLog', '[0],[system],[loginLog],', '1', null, '/loginLog/delLoginLog');
INSERT INTO `t_sys_menu` VALUES ('63', null, null, null, null, 'login_log_list', '', '0', null, '3', 'Login Log List', '2', 'loginLog', '[0],[system],[loginLog],', '1', null, '/loginLog/list');
INSERT INTO `t_sys_menu` VALUES ('37', null, null, null, null, 'to_role_edit', '', '0', null, '3', 'Role Edit Redirect', '5', 'role', '[0],[system],[role],', '1', null, '/role/role_edit');
INSERT INTO `t_sys_menu` VALUES ('38', null, null, null, null, 'to_role_assign', '', '0', null, '3', 'Role Assign Redirect', '6', 'role', '[0],[system],[role],', '1', null, '/role/role_assign');
INSERT INTO `t_sys_menu` VALUES ('39', null, null, null, null, 'role_list', '', '0', null, '3', 'Role List', '7', 'role', '[0],[system],[role],', '1', null, '/role/list');
INSERT INTO `t_sys_menu` VALUES ('40', null, null, null, null, 'to_assign_role', '', '0', null, '3', 'Assign Role Redirect', '8', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/role_assign');
INSERT INTO `t_sys_menu` VALUES ('41', null, null, null, null, 'to_user_edit', '', '0', null, '3', 'User Edit Redirect', '9', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/user_edit');
INSERT INTO `t_sys_menu` VALUES ('42', null, null, null, null, 'mgr_list', '', '0', null, '3', 'User List', '10', 'mgr', '[0],[system],[mgr],', '1', null, '/mgr/list');
INSERT INTO `t_sys_menu` VALUES ('43', null, null, null, null, 'cfg', '', '1', null, '2', 'Configuration Management', '10', 'system', '[0],[system],', '1', null, '/cfg');
INSERT INTO `t_sys_menu` VALUES ('44', null, null, null, null, 'cfg_add', '', '0', null, '3', 'Add Configuration', '1', 'cfg', '[0],[system],[cfg],', '1', null, '/cfg/add');
INSERT INTO `t_sys_menu` VALUES ('45', null, null, null, null, 'cfg_update', '', '0', null, '3', 'Edit Configuration', '2', 'cfg', '[0],[system],[cfg],', '1', null, '/cfg/update');
INSERT INTO `t_sys_menu` VALUES ('46', null, null, null, null, 'cfg_delete', '', '0', null, '3', 'Delete Configuration', '3', 'cfg', '[0],[system],[cfg],', '1', null, '/cfg/delete');
INSERT INTO `t_sys_menu` VALUES ('47', null, null, null, null, 'task', '', '1', null, '2', 'Task Management', '11', 'system', '[0],[system],', '1', null, '/task');
INSERT INTO `t_sys_menu` VALUES ('48', null, null, null, null, 'task_add', '', '0', null, '3', 'Add Task', '1', 'task', '[0],[system],[task],', '1', null, '/task/add');
INSERT INTO `t_sys_menu` VALUES ('49', null, null, null, null, 'task_update', '', '0', null, '3', 'Edit Task', '2', 'task', '[0],[system],[task],', '1', null, '/task/update');
INSERT INTO `t_sys_menu` VALUES ('50', null, null, null, null, 'task_delete', '', '0', null, '3', 'Delete Task', '3', 'task', '[0],[system],[task],', '1', null, '/task/delete');
INSERT INTO `t_sys_menu` VALUES ('56', '1', '2019-03-11 22:30:17', '1', '2019-03-11 22:30:17', 'editArticle', '', '1', null, '3', 'Edit Article', '1', 'article', '[0],[cms],[article]', '1', null, '/article/edit');
INSERT INTO `t_sys_menu` VALUES ('3', null, null, '47', '2019-06-02 10:09:09', 'operationMgr', 'fa-wrench', '1', null, '1', 'Operations Management', '3', '0', '[0],', '1', null, '/optionMgr');
INSERT INTO `t_sys_menu` VALUES ('64', '47', '2019-06-02 10:10:20', '47', '2019-06-02 10:10:20', 'druid', '', '1', null, '2', 'Database Monitoring', '1', 'operationMgr', '[0],[operationMgr],', '1', null, '/druid');
INSERT INTO `t_sys_menu` VALUES ('65', '47', '2019-06-02 10:10:20', '47', '2019-06-02 10:10:20', 'swagger', null, '1', null, '2', 'API Documentation', '2', 'operationMgr', '[0],[operationMgr],', '1', null, '/swagger-ui.html');
INSERT INTO `t_sys_menu` VALUES ('66', '1', '2019-06-10 21:26:52', '1', '2019-06-10 21:26:52', 'messageMgr', 'fa-envelope-o', '1', null, '1', 'Message Management', '5', '0', '[0],', '1', null, '/message');
INSERT INTO `t_sys_menu` VALUES ('67', '1', '2019-06-10 21:27:34', '1', '2019-06-10 21:27:34', 'historyMessage', null, '1', null, '2', 'Message History', '1', 'messageMgr', '[0],[messageMgr],', '1', null, '/message/history');
INSERT INTO `t_sys_menu` VALUES ('68', '1', '2019-06-10 21:27:56', '1', '2019-06-10 21:27:56', 'messageTemplate', null, '1', null, '2', 'Message Template', '2', 'messageMgr', '[0],[messageMgr],', '1', null, '/message/template');
INSERT INTO `t_sys_menu` VALUES ('69', '1', '2019-06-10 21:28:21', '1', '2019-06-10 21:28:21', 'messageSender', null, '1', null, '2', 'Message Sender', '3', 'messageMgr', '[0],[messageMgr],', '1', null, '/message/sender');

-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------
INSERT INTO `t_sys_notice` VALUES ('1', '1', '2017-01-11 08:53:20', '1', '2019-01-08 23:30:58', 'Welcome to the guns-lite admin system. Click to view <a href=\"http://enilu.gitee.io/guns-lite\" target=\"_blank\">official documentation</a>', 'World', '10');
INSERT INTO `t_sys_notice` VALUES ('2', '1', '2017-01-11 08:53:20', '1', '2019-01-08 23:30:58', 'Up to ￥2000 Alibaba Cloud general voucher, <a href=\"https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=alts44ap\" target=\"_blank\">click to view</a>', 'Up to ￥2000 Alibaba Cloud general voucher, <a href=\"https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=alts44ap\" target=\"_blank\">click to view</a>', '10');

-- ----------------------------
-- Records of t_sys_operation_log
-- ----------------------------
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('76', 'Business Log', 'Edit Article', '1', 'cn.enilu.guns.api.controller.cms.ArticleMgrController', 'upload', '2019-05-10 13:22:49', 'Success', 'name=null;;;');
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('77', 'Business Log', 'Edit Article', '1', 'cn.enilu.guns.api.controller.cms.ArticleMgrController', 'upload', '2019-05-10 13:31:09', 'Success', 'name=null;;;');

-- ----------------------------
-- Records of t_sys_relation
-- ----------------------------
INSERT INTO `t_sys_relation` VALUES ('72', '65', '1');
INSERT INTO `t_sys_relation` VALUES ('71', '64', '1');
INSERT INTO `t_sys_relation` VALUES ('70', '63', '1');
INSERT INTO `t_sys_relation` VALUES ('69', '62', '1');
INSERT INTO `t_sys_relation` VALUES ('68', '59', '1');
INSERT INTO `t_sys_relation` VALUES ('67', '61', '1');
INSERT INTO `t_sys_relation` VALUES ('66', '60', '1');
INSERT INTO `t_sys_relation` VALUES ('65', '58', '1');
INSERT INTO `t_sys_relation` VALUES ('64', '3', '1');
INSERT INTO `t_sys_relation` VALUES ('60', '56', '1');
INSERT INTO `t_sys_relation` VALUES ('57', '2', '1');
INSERT INTO `t_sys_relation` VALUES ('55', '50', '1');
INSERT INTO `t_sys_relation` VALUES ('54', '49', '1');
INSERT INTO `t_sys_relation` VALUES ('53', '48', '1');
INSERT INTO `t_sys_relation` VALUES ('52', '47', '1');
INSERT INTO `t_sys_relation` VALUES ('51', '46', '1');
INSERT INTO `t_sys_relation` VALUES ('50', '45', '1');
INSERT INTO `t_sys_relation` VALUES ('49', '44', '1');
INSERT INTO `t_sys_relation` VALUES ('48', '43', '1');
INSERT INTO `t_sys_relation` VALUES ('47', '35', '1');
INSERT INTO `t_sys_relation` VALUES ('46', '34', '1');
INSERT INTO `t_sys_relation` VALUES ('45', '33', '1');
INSERT INTO `t_sys_relation` VALUES ('44', '27', '1');
INSERT INTO `t_sys_relation` VALUES ('43', '26', '1');
INSERT INTO `t_sys_relation` VALUES ('42', '25', '1');
INSERT INTO `t_sys_relation` VALUES ('41', '22', '1');
INSERT INTO `t_sys_relation` VALUES ('40', '36', '1');
INSERT INTO `t_sys_relation` VALUES ('39', '32', '1');
INSERT INTO `t_sys_relation` VALUES ('38', '31', '1');
INSERT INTO `t_sys_relation` VALUES ('37', '30', '1');
INSERT INTO `t_sys_relation` VALUES ('36', '24', '1');
INSERT INTO `t_sys_relation` VALUES ('35', '23', '1');
INSERT INTO `t_sys_relation` VALUES ('34', '21', '1');
INSERT INTO `t_sys_relation` VALUES ('33', '29', '1');
INSERT INTO `t_sys_relation` VALUES ('32', '28', '1');
INSERT INTO `t_sys_relation` VALUES ('31', '20', '1');
INSERT INTO `t_sys_relation` VALUES ('30', '19', '1');
INSERT INTO `t_sys_relation` VALUES ('29', '18', '1');
INSERT INTO `t_sys_relation` VALUES ('28', '17', '1');
INSERT INTO `t_sys_relation` VALUES ('27', '39', '1');
INSERT INTO `t_sys_relation` VALUES ('26', '38', '1');
INSERT INTO `t_sys_relation` VALUES ('25', '37', '1');
INSERT INTO `t_sys_relation` VALUES ('24', '16', '1');
INSERT INTO `t_sys_relation` VALUES ('23', '15', '1');
INSERT INTO `t_sys_relation` VALUES ('22', '14', '1');
INSERT INTO `t_sys_relation` VALUES ('21', '13', '1');
INSERT INTO `t_sys_relation` VALUES ('20', '12', '1');
INSERT INTO `t_sys_relation` VALUES ('19', '42', '1');
INSERT INTO `t_sys_relation` VALUES ('18', '41', '1');
INSERT INTO `t_sys_relation` VALUES ('17', '40', '1');
INSERT INTO `t_sys_relation` VALUES ('16', '11', '1');
INSERT INTO `t_sys_relation` VALUES ('15', '10', '1');
INSERT INTO `t_sys_relation` VALUES ('14', '9', '1');
INSERT INTO `t_sys_relation` VALUES ('13', '8', '1');
INSERT INTO `t_sys_relation` VALUES ('12', '7', '1');
INSERT INTO `t_sys_relation` VALUES ('11', '6', '1');
INSERT INTO `t_sys_relation` VALUES ('10', '5', '1');
INSERT INTO `t_sys_relation` VALUES ('9', '4', '1');
INSERT INTO `t_sys_relation` VALUES ('8', '1', '1');
INSERT INTO `t_sys_relation` VALUES ('7', '56', '2');
INSERT INTO `t_sys_relation` VALUES ('73', '66', '1');
INSERT INTO `t_sys_relation` VALUES ('74', '67', '1');
INSERT INTO `t_sys_relation` VALUES ('75', '68', '1');
INSERT INTO `t_sys_relation` VALUES ('76', '69', '1');


-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', null, null, null, null, '24', 'Super Admin', '1', '0', 'administrator', '1');
INSERT INTO `t_sys_role` VALUES ('2', null, null, null, null, '25', 'Website Admin', '1', '1', 'developer', null);

-- ----------------------------
-- Records of t_sys_task
-- ----------------------------
INSERT INTO `t_sys_task` (`id`, `name`, `job_group`, `job_class`, `note`, `cron`, `data`, `exec_at`, `exec_result`, `disabled`, `create_time`, `create_by`, `concurrent`, `modify_time`, `modify_by`) VALUES ('1', '测试任务', 'default', 'cn.enilu.guns.service.task.job.HelloJob', '测试任务,每30分钟行一次', '0 0/30 * * * ?', '{\n\"appname\": \"guns-lite\",\n\"version\":1\n}\n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            ', '2019-03-27 11:47:00', '执行成功', '0', '2018-12-28 09:54:00', '1', '0', '2019-03-27 11:47:11', '-1');

-- ----------------------------
-- Records of t_sys_task_log
-- ----------------------------

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------

INSERT INTO `t_sys_user` VALUES ('-1', null, null, null, null, 'system', null, null, null, null, 'Application System', 'avatar.png', null, null, null, null, null, null);
INSERT INTO `t_sys_user` VALUES ('1', null, '2016-01-29 08:49:53', '1', '2019-03-20 23:45:24', 'admin', 'avatar.png', '2017-05-05 00:00:00', '3', 'eniluzt@qq.com', 'Admin', '6ab1f386d715cfb6be85de941d438b02', null, '1', '8pgby', '2', '1', '25');
INSERT INTO `t_sys_user` VALUES ('2', null, '2018-09-13 17:21:02', '1', '2019-01-09 23:05:51', 'developer', 'avatar.png', '2017-12-31 00:00:00', '4', 'eniluzt@qq.com', 'Website Admin', '4552805b07a4bf92ce1cea0373aab868', '', '2', 'vscp9', '1', '1', null);

-- ----------------------------
-- Records of t_test_boy
-- ----------------------------
INSERT INTO `t_test_boy` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `age`, `birthday`, `has_girl_friend`, `name`) VALUES ('1', null, null, null, null, '18', '2000-01-01', '1', 'Jane Doe');


-- ----------------------------
-- Records of t_message_sender
-- ----------------------------
INSERT INTO `t_message_sender` VALUES 
('1', null, null, null, null, 'tencentSmsSender', 'Tencent SMS Service', null);

INSERT INTO `t_message_sender` VALUES 
('2', null, null, null, null, 'defaultEmailSender', 'Default Email Sender', null);

-- ----------------------------
-- Records of t_message_template
-- ----------------------------
INSERT INTO `t_message_template` VALUES 
('1', null, null, null, null, 'REGISTER_CODE', 'Registration page, click to get verification code', '[Tencent Cloud] Verification code {1}, please complete verification within 5 minutes. Ignore if not your action.', '1', 'Registration Code', 0);

INSERT INTO `t_message_template` VALUES 
('2', null, null, null, null, 'EMAIL_TEST', 'Test sending', 'Hello {1}, welcome to {2}', '2', 'Test Email', 1);

INSERT INTO `t_message_template` VALUES 
('3', null, null, null, null, 'EMAIL_HTML_TEMPLATE_TEST', 'Test sending template email', 'Hello <strong>${userName}</strong>, welcome to <font color=\"red\">${appName}</font>, this is an HTML template email', '2', 'Template Email Test', 1);

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES 
('1', null, '2019-06-10 21:20:16', null, null, '[Tencent Cloud] Verification code 1032, please complete verification within 5 minutes. Ignore if not your action.', '15021592814', '2', 'REGISTER_CODE', '0');
