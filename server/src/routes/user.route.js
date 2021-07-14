const express = require('express');
const auth = require('../middlewares/auth');
const validate = require('../middlewares/validate');
const userValidation = require('../validations/user.validation');
const userController = require('../controllers/user.controller');

const router = express.Router();
router
  .route('/nickname')
  .post([auth, validate(userValidation.changeName)], userController.changeName);
module.exports = router;
