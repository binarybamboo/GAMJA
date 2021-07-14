const express = require('express');
const validate = require('../middlewares/validate');
const diaryValidation = require('../validations/diary.validation');
const diaryController = require('../controllers/diary.controller');
const auth = require('../middlewares/auth');

const router = express.Router();

module.exports = router;
