const express = require('express');
const validate = require('../middlewares/validate');
const { upload } = require('../middlewares/upload');
const diaryValidation = require('../validations/diary.validation');
const diaryController = require('../controllers/diary.controller');
const auth = require('../middlewares/auth');

const router = express.Router();

router
  .route('/')
  .post(
    [auth, validate(diaryValidation.createDiary), upload.array('img', 5)],
    diaryController.createDiary,
  );

router
  .route('/:diaryGroupId')
  .get([auth, validate(diaryValidation.getDiary)], diaryController.getDiary);

router
  .route('/:diaryId')
  .patch(
    [auth, validate(diaryValidation.updateDiary)],
    diaryController.updateDiary,
  )
  .delete(
    [auth, validate(diaryValidation.deleteDiary)],
    diaryController.deleteDiary,
  );

module.exports = router;
