/* eslint-disable no-underscore-dangle */
const mongoose = require('mongoose');
const httpStatus = require('http-status');
const language = require('@google-cloud/language');
const { Translate } = require('@google-cloud/translate').v2;
const { Diary } = require('../models');
const { s3 } = require('../middlewares/upload');
const ApiError = require('../utils/ApiError');
const diaryGroupService = require('./diary-group.service');
const translateKey = require('../config/translate.json');

const client = new language.LanguageServiceClient();
const translate = new Translate({
  credentials: translateKey,
  projectId: translateKey.project_id,
});
const createDiary = async req => {
  const { files } = req;
  const locationArray = files.map(_ => {
    return _.location;
  });
  const target = 'en';

  const keyArray = files.map(_ => {
    return _.key;
  });
  const text = req.body.description;
  const [translation] = await translate.translate(text, target);
  const document = {
    content: translation,
    type: 'PLAIN_TEXT',
  };
  const results = await client.analyzeSentiment({ document });
  const sentiment = results[0].documentSentiment;

  const diary = await Diary.create({
    name: req.body.name,
    description: req.body.description,
    author: mongoose.Types.ObjectId(req.user._id),
    photo: locationArray,
    photo_key: keyArray,
    state: sentiment.score * 100,
  });
  await diaryGroupService.addDiary(req.body.diaryGroupId, diary._id);
  return diary;
};

const getDiary = async req => {
  const { diaryGroupId } = req.query;
  const diary = await diaryGroupService.printAll(diaryGroupId);
  return diary;
};

const updateDiaryById = async req => {
  const diary = await Diary.findOneAndUpdate(
    {
      _id: req.params.diaryId,
      author: mongoose.Types.ObjectId(req.user._id),
    },
    { $set: req.body },
    { returnNewDocument: true },
  );
  return diary;
};

const deleteDiaryById = async req => {
  const diary = await Diary.findOne({ _id: req.params.diaryId });
  const keyArray = diary.photo_key;
  const objects = keyArray.map(_ => ({ Key: _ }));
  const options = {
    Bucket: 'gamja-profile-bucket',
    Delete: { Objects: objects },
  };
  await s3.deleteObjects(options, (err, data) => {
    if (err) throw new ApiError(httpStatus.BAD_REQUEST, err.message);
    else console.log(data);
  });
  await Diary.deleteOne({
    _id: req.params.diaryId,
    author: mongoose.Types.ObjectId(req.user._id),
  });
};

module.exports = {
  createDiary,
  getDiary,
  updateDiaryById,
  deleteDiaryById,
};
