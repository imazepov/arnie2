/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.fortius.arnie.data.interop;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-04-21")
public class ExerciseSet implements org.apache.thrift.TBase<ExerciseSet, ExerciseSet._Fields>, java.io.Serializable, Cloneable, Comparable<ExerciseSet>, android.os.Parcelable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ExerciseSet");

  private static final org.apache.thrift.protocol.TField EXERCISE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("exerciseId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField REPS_FIELD_DESC = new org.apache.thrift.protocol.TField("reps", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField WEIGHT_FIELD_DESC = new org.apache.thrift.protocol.TField("weight", org.apache.thrift.protocol.TType.DOUBLE, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ExerciseSetStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ExerciseSetTupleSchemeFactory());
  }

  public String exerciseId; // required
  public long timestamp; // required
  public int reps; // required
  public double weight; // required

  @Override
  public void writeToParcel(android.os.Parcel out, int flags) {
    //primitive bitfield of type: byte
    out.writeByte(__isset_bitfield);

    out.writeString(exerciseId);
    out.writeLong(timestamp);
    out.writeInt(reps);
    out.writeDouble(weight);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public ExerciseSet(android.os.Parcel in) {
    //primitive bitfield of type: byte
    __isset_bitfield = in.readByte();

    this.exerciseId= in.readString();
    this.timestamp = in.readLong();
    this.reps = in.readInt();
    this.weight = in.readDouble();
  }

  public static final android.os.Parcelable.Creator<ExerciseSet> CREATOR = new android.os.Parcelable.Creator<ExerciseSet>() {
    @Override
    public ExerciseSet[] newArray(int size) {
      return new ExerciseSet[size];
    }

    @Override
    public ExerciseSet createFromParcel(android.os.Parcel in) {
      return new ExerciseSet(in);
    }
  };

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EXERCISE_ID((short)1, "exerciseId"),
    TIMESTAMP((short)2, "timestamp"),
    REPS((short)3, "reps"),
    WEIGHT((short)4, "weight");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // EXERCISE_ID
          return EXERCISE_ID;
        case 2: // TIMESTAMP
          return TIMESTAMP;
        case 3: // REPS
          return REPS;
        case 4: // WEIGHT
          return WEIGHT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TIMESTAMP_ISSET_ID = 0;
  private static final int __REPS_ISSET_ID = 1;
  private static final int __WEIGHT_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EXERCISE_ID, new org.apache.thrift.meta_data.FieldMetaData("exerciseId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.REPS, new org.apache.thrift.meta_data.FieldMetaData("reps", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.WEIGHT, new org.apache.thrift.meta_data.FieldMetaData("weight", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ExerciseSet.class, metaDataMap);
  }

  public ExerciseSet() {
  }

  public ExerciseSet(
    String exerciseId,
    long timestamp,
    int reps,
    double weight)
  {
    this();
    this.exerciseId = exerciseId;
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    this.reps = reps;
    setRepsIsSet(true);
    this.weight = weight;
    setWeightIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ExerciseSet(ExerciseSet other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetExerciseId()) {
      this.exerciseId = other.exerciseId;
    }
    this.timestamp = other.timestamp;
    this.reps = other.reps;
    this.weight = other.weight;
  }

  public ExerciseSet deepCopy() {
    return new ExerciseSet(this);
  }

  @Override
  public void clear() {
    this.exerciseId = null;
    setTimestampIsSet(false);
    this.timestamp = 0;
    setRepsIsSet(false);
    this.reps = 0;
    setWeightIsSet(false);
    this.weight = 0.0;
  }

  public String getExerciseId() {
    return this.exerciseId;
  }

  public ExerciseSet setExerciseId(String exerciseId) {
    this.exerciseId = exerciseId;
    return this;
  }

  public void unsetExerciseId() {
    this.exerciseId = null;
  }

  /** Returns true if field exerciseId is set (has been assigned a value) and false otherwise */
  public boolean isSetExerciseId() {
    return this.exerciseId != null;
  }

  public void setExerciseIdIsSet(boolean value) {
    if (!value) {
      this.exerciseId = null;
    }
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public ExerciseSet setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    return this;
  }

  public void unsetTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  /** Returns true if field timestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  public void setTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIMESTAMP_ISSET_ID, value);
  }

  public int getReps() {
    return this.reps;
  }

  public ExerciseSet setReps(int reps) {
    this.reps = reps;
    setRepsIsSet(true);
    return this;
  }

  public void unsetReps() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __REPS_ISSET_ID);
  }

  /** Returns true if field reps is set (has been assigned a value) and false otherwise */
  public boolean isSetReps() {
    return EncodingUtils.testBit(__isset_bitfield, __REPS_ISSET_ID);
  }

  public void setRepsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __REPS_ISSET_ID, value);
  }

  public double getWeight() {
    return this.weight;
  }

  public ExerciseSet setWeight(double weight) {
    this.weight = weight;
    setWeightIsSet(true);
    return this;
  }

  public void unsetWeight() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WEIGHT_ISSET_ID);
  }

  /** Returns true if field weight is set (has been assigned a value) and false otherwise */
  public boolean isSetWeight() {
    return EncodingUtils.testBit(__isset_bitfield, __WEIGHT_ISSET_ID);
  }

  public void setWeightIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WEIGHT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case EXERCISE_ID:
      if (value == null) {
        unsetExerciseId();
      } else {
        setExerciseId((String)value);
      }
      break;

    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((Long)value);
      }
      break;

    case REPS:
      if (value == null) {
        unsetReps();
      } else {
        setReps((Integer)value);
      }
      break;

    case WEIGHT:
      if (value == null) {
        unsetWeight();
      } else {
        setWeight((Double)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case EXERCISE_ID:
      return getExerciseId();

    case TIMESTAMP:
      return getTimestamp();

    case REPS:
      return getReps();

    case WEIGHT:
      return getWeight();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case EXERCISE_ID:
      return isSetExerciseId();
    case TIMESTAMP:
      return isSetTimestamp();
    case REPS:
      return isSetReps();
    case WEIGHT:
      return isSetWeight();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ExerciseSet)
      return this.equals((ExerciseSet)that);
    return false;
  }

  public boolean equals(ExerciseSet that) {
    if (that == null)
      return false;

    boolean this_present_exerciseId = true && this.isSetExerciseId();
    boolean that_present_exerciseId = true && that.isSetExerciseId();
    if (this_present_exerciseId || that_present_exerciseId) {
      if (!(this_present_exerciseId && that_present_exerciseId))
        return false;
      if (!this.exerciseId.equals(that.exerciseId))
        return false;
    }

    boolean this_present_timestamp = true;
    boolean that_present_timestamp = true;
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (this.timestamp != that.timestamp)
        return false;
    }

    boolean this_present_reps = true;
    boolean that_present_reps = true;
    if (this_present_reps || that_present_reps) {
      if (!(this_present_reps && that_present_reps))
        return false;
      if (this.reps != that.reps)
        return false;
    }

    boolean this_present_weight = true;
    boolean that_present_weight = true;
    if (this_present_weight || that_present_weight) {
      if (!(this_present_weight && that_present_weight))
        return false;
      if (this.weight != that.weight)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_exerciseId = true && (isSetExerciseId());
    list.add(present_exerciseId);
    if (present_exerciseId)
      list.add(exerciseId);

    boolean present_timestamp = true;
    list.add(present_timestamp);
    if (present_timestamp)
      list.add(timestamp);

    boolean present_reps = true;
    list.add(present_reps);
    if (present_reps)
      list.add(reps);

    boolean present_weight = true;
    list.add(present_weight);
    if (present_weight)
      list.add(weight);

    return list.hashCode();
  }

  @Override
  public int compareTo(ExerciseSet other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetExerciseId()).compareTo(other.isSetExerciseId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExerciseId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.exerciseId, other.exerciseId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(other.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, other.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReps()).compareTo(other.isSetReps());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReps()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reps, other.reps);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWeight()).compareTo(other.isSetWeight());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWeight()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.weight, other.weight);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ExerciseSet(");
    boolean first = true;

    sb.append("exerciseId:");
    if (this.exerciseId == null) {
      sb.append("null");
    } else {
      sb.append(this.exerciseId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("timestamp:");
    sb.append(this.timestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("reps:");
    sb.append(this.reps);
    first = false;
    if (!first) sb.append(", ");
    sb.append("weight:");
    sb.append(this.weight);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ExerciseSetStandardSchemeFactory implements SchemeFactory {
    public ExerciseSetStandardScheme getScheme() {
      return new ExerciseSetStandardScheme();
    }
  }

  private static class ExerciseSetStandardScheme extends StandardScheme<ExerciseSet> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ExerciseSet struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EXERCISE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.exerciseId = iprot.readString();
              struct.setExerciseIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timestamp = iprot.readI64();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // REPS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.reps = iprot.readI32();
              struct.setRepsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // WEIGHT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.weight = iprot.readDouble();
              struct.setWeightIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ExerciseSet struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.exerciseId != null) {
        oprot.writeFieldBegin(EXERCISE_ID_FIELD_DESC);
        oprot.writeString(struct.exerciseId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(REPS_FIELD_DESC);
      oprot.writeI32(struct.reps);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(WEIGHT_FIELD_DESC);
      oprot.writeDouble(struct.weight);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ExerciseSetTupleSchemeFactory implements SchemeFactory {
    public ExerciseSetTupleScheme getScheme() {
      return new ExerciseSetTupleScheme();
    }
  }

  private static class ExerciseSetTupleScheme extends TupleScheme<ExerciseSet> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ExerciseSet struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetExerciseId()) {
        optionals.set(0);
      }
      if (struct.isSetTimestamp()) {
        optionals.set(1);
      }
      if (struct.isSetReps()) {
        optionals.set(2);
      }
      if (struct.isSetWeight()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetExerciseId()) {
        oprot.writeString(struct.exerciseId);
      }
      if (struct.isSetTimestamp()) {
        oprot.writeI64(struct.timestamp);
      }
      if (struct.isSetReps()) {
        oprot.writeI32(struct.reps);
      }
      if (struct.isSetWeight()) {
        oprot.writeDouble(struct.weight);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ExerciseSet struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.exerciseId = iprot.readString();
        struct.setExerciseIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.timestamp = iprot.readI64();
        struct.setTimestampIsSet(true);
      }
      if (incoming.get(2)) {
        struct.reps = iprot.readI32();
        struct.setRepsIsSet(true);
      }
      if (incoming.get(3)) {
        struct.weight = iprot.readDouble();
        struct.setWeightIsSet(true);
      }
    }
  }

}

