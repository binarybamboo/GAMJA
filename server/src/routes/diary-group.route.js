const express = require('express');
const validate = require('../middlewares/validate');
const diaryGroupValidation = require('../validations/diary-group.validation');
const diaryGroupController = require('../controllers/diary-group.controller');
const auth = require('../middlewares/auth');

const router = express.Router();
router
  .route('/')
  .get(
    [auth, validate(diaryGroupValidation.getDiaryGroups)],
    diaryGroupController.getDiaryGroups,
  )
  .post(
    [auth, validate(diaryGroupValidation.createDiaryGroup)],
    diaryGroupController.createDiaryGroup,
  );

router
  .route('/:diaryGroupId')
  .patch(
    [auth, validate(diaryGroupValidation.updateDiaryGroup)],
    diaryGroupController.updateDiaryGroup,
  )
  .delete(
    [auth, validate(diaryGroupValidation.deleteDiaryGroup)],
    diaryGroupController.deleteDiaryGroup,
  );

module.exports = router;
