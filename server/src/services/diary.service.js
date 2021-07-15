/* eslint-disable no-underscore-dangle */
const httpStatus = require('http-status');
const mongoose = require('mongoose');
const { createLogger } = require('winston');
const { Diary } = require('../models');
const ApiError = require('../utils/ApiError');
const { s3 } = require('../middlewares/upload');
const logger = require('../config/logger');
const diaryGroupService = require('./diary-group.service');

const createDiary = async req => {
  const { files } = req;
  const locationArray = files.map(_ => {
    return _.location;
  });
  const keyArray = files.map(_ => {
    return _.key;
  });
  console.log(req.body);
  // diaryGroupService.addDiary(parentId,);
  return Diary.create({
    name: req.body.name,
    description: req.body.description,
    author: mongoose.Types.ObjectId(req.user._id),
    parentId: req.body.parentId,
    /* photo: be soon */
    photo: locationArray,
    photo_key: keyArray,
    /* state: be soon */
  });
};
const getDiary = async req => {
  const { diaryGroupId } = req.body;
  return diaryGroupService.printAll(diaryGroupId);
};

const updateDiaryById = async req => {
  return Diary.findOneAndUpdate(
    {
      _id: req.params.diaryId,
      author: mongoose.Types.ObjectId(req.user._id),
    },
    { $set: req.body },
    { returnNewDocument: true },
  );
};

const deleteDiaryById = async req => {
  const diary = await Diary.findOne({ _id: req.params.diaryId });
  const keyArray = diary.photo_key;
  logger.info(keyArray);
  const objects = keyArray.map(_ => ({ Key: _ }));
  const options = {
    Bucket: 'gamja-profile-bucket',
    Delete: { Objects: objects },
  };
  await s3.deleteObjects(options, (err, data) => {
    if (err) console.log(err, err.stack);
    else console.log(data);
  });
  return Diary.deleteOne({
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
