/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package arnie.data.interop;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2015-12-08")
public class WorkoutSession implements org.apache.thrift.TBase<WorkoutSession, WorkoutSession._Fields>, java.io.Serializable, Cloneable, Comparable<WorkoutSession>, android.os.Parcelable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WorkoutSession");

  private static final org.apache.thrift.protocol.TField START_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("startTimestamp", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField END_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("endTimestamp", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField SETS_FIELD_DESC = new org.apache.thrift.protocol.TField("sets", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new WorkoutSessionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new WorkoutSessionTupleSchemeFactory());
  }

  public long startTimestamp; // required
  public long endTimestamp; // required
  public List<ExerciseSet> sets; // required

  @Override
  public void writeToParcel(android.os.Parcel out, int flags) {
    //primitive bitfield of type: byte
    out.writeByte(__isset_bitfield);

    out.writeLong(startTimestamp);
    out.writeLong(endTimestamp);
    out.writeTypedList(sets);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public WorkoutSession(android.os.Parcel in) {
    //primitive bitfield of type: byte
    __isset_bitfield = in.readByte();

    this.startTimestamp = in.readLong();
    this.endTimestamp = in.readLong();
    this.sets = new ArrayList<ExerciseSet>();
    in.readTypedList(this.sets, ExerciseSet.CREATOR);
  }

  public static final android.os.Parcelable.Creator<WorkoutSession> CREATOR = new android.os.Parcelable.Creator<WorkoutSession>() {
    @Override
    public WorkoutSession[] newArray(int size) {
      return new WorkoutSession[size];
    }

    @Override
    public WorkoutSession createFromParcel(android.os.Parcel in) {
      return new WorkoutSession(in);
    }
  };

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    START_TIMESTAMP((short)1, "startTimestamp"),
    END_TIMESTAMP((short)2, "endTimestamp"),
    SETS((short)3, "sets");

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
        case 1: // START_TIMESTAMP
          return START_TIMESTAMP;
        case 2: // END_TIMESTAMP
          return END_TIMESTAMP;
        case 3: // SETS
          return SETS;
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
  private static final int __STARTTIMESTAMP_ISSET_ID = 0;
  private static final int __ENDTIMESTAMP_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.START_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("startTimestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.END_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("endTimestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SETS, new org.apache.thrift.meta_data.FieldMetaData("sets", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ExerciseSet.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WorkoutSession.class, metaDataMap);
  }

  public WorkoutSession() {
  }

  public WorkoutSession(
    long startTimestamp,
    long endTimestamp,
    List<ExerciseSet> sets)
  {
    this();
    this.startTimestamp = startTimestamp;
    setStartTimestampIsSet(true);
    this.endTimestamp = endTimestamp;
    setEndTimestampIsSet(true);
    this.sets = sets;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WorkoutSession(WorkoutSession other) {
    __isset_bitfield = other.__isset_bitfield;
    this.startTimestamp = other.startTimestamp;
    this.endTimestamp = other.endTimestamp;
    if (other.isSetSets()) {
      List<ExerciseSet> __this__sets = new ArrayList<ExerciseSet>(other.sets.size());
      for (ExerciseSet other_element : other.sets) {
        __this__sets.add(new ExerciseSet(other_element));
      }
      this.sets = __this__sets;
    }
  }

  public WorkoutSession deepCopy() {
    return new WorkoutSession(this);
  }

  @Override
  public void clear() {
    setStartTimestampIsSet(false);
    this.startTimestamp = 0;
    setEndTimestampIsSet(false);
    this.endTimestamp = 0;
    this.sets = null;
  }

  public long getStartTimestamp() {
    return this.startTimestamp;
  }

  public WorkoutSession setStartTimestamp(long startTimestamp) {
    this.startTimestamp = startTimestamp;
    setStartTimestampIsSet(true);
    return this;
  }

  public void unsetStartTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STARTTIMESTAMP_ISSET_ID);
  }

  /** Returns true if field startTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetStartTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __STARTTIMESTAMP_ISSET_ID);
  }

  public void setStartTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STARTTIMESTAMP_ISSET_ID, value);
  }

  public long getEndTimestamp() {
    return this.endTimestamp;
  }

  public WorkoutSession setEndTimestamp(long endTimestamp) {
    this.endTimestamp = endTimestamp;
    setEndTimestampIsSet(true);
    return this;
  }

  public void unsetEndTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ENDTIMESTAMP_ISSET_ID);
  }

  /** Returns true if field endTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetEndTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __ENDTIMESTAMP_ISSET_ID);
  }

  public void setEndTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ENDTIMESTAMP_ISSET_ID, value);
  }

  public int getSetsSize() {
    return (this.sets == null) ? 0 : this.sets.size();
  }

  public java.util.Iterator<ExerciseSet> getSetsIterator() {
    return (this.sets == null) ? null : this.sets.iterator();
  }

  public void addToSets(ExerciseSet elem) {
    if (this.sets == null) {
      this.sets = new ArrayList<ExerciseSet>();
    }
    this.sets.add(elem);
  }

  public List<ExerciseSet> getSets() {
    return this.sets;
  }

  public WorkoutSession setSets(List<ExerciseSet> sets) {
    this.sets = sets;
    return this;
  }

  public void unsetSets() {
    this.sets = null;
  }

  /** Returns true if field sets is set (has been assigned a value) and false otherwise */
  public boolean isSetSets() {
    return this.sets != null;
  }

  public void setSetsIsSet(boolean value) {
    if (!value) {
      this.sets = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case START_TIMESTAMP:
      if (value == null) {
        unsetStartTimestamp();
      } else {
        setStartTimestamp((Long)value);
      }
      break;

    case END_TIMESTAMP:
      if (value == null) {
        unsetEndTimestamp();
      } else {
        setEndTimestamp((Long)value);
      }
      break;

    case SETS:
      if (value == null) {
        unsetSets();
      } else {
        setSets((List<ExerciseSet>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case START_TIMESTAMP:
      return getStartTimestamp();

    case END_TIMESTAMP:
      return getEndTimestamp();

    case SETS:
      return getSets();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case START_TIMESTAMP:
      return isSetStartTimestamp();
    case END_TIMESTAMP:
      return isSetEndTimestamp();
    case SETS:
      return isSetSets();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WorkoutSession)
      return this.equals((WorkoutSession)that);
    return false;
  }

  public boolean equals(WorkoutSession that) {
    if (that == null)
      return false;

    boolean this_present_startTimestamp = true;
    boolean that_present_startTimestamp = true;
    if (this_present_startTimestamp || that_present_startTimestamp) {
      if (!(this_present_startTimestamp && that_present_startTimestamp))
        return false;
      if (this.startTimestamp != that.startTimestamp)
        return false;
    }

    boolean this_present_endTimestamp = true;
    boolean that_present_endTimestamp = true;
    if (this_present_endTimestamp || that_present_endTimestamp) {
      if (!(this_present_endTimestamp && that_present_endTimestamp))
        return false;
      if (this.endTimestamp != that.endTimestamp)
        return false;
    }

    boolean this_present_sets = true && this.isSetSets();
    boolean that_present_sets = true && that.isSetSets();
    if (this_present_sets || that_present_sets) {
      if (!(this_present_sets && that_present_sets))
        return false;
      if (!this.sets.equals(that.sets))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_startTimestamp = true;
    list.add(present_startTimestamp);
    if (present_startTimestamp)
      list.add(startTimestamp);

    boolean present_endTimestamp = true;
    list.add(present_endTimestamp);
    if (present_endTimestamp)
      list.add(endTimestamp);

    boolean present_sets = true && (isSetSets());
    list.add(present_sets);
    if (present_sets)
      list.add(sets);

    return list.hashCode();
  }

  @Override
  public int compareTo(WorkoutSession other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetStartTimestamp()).compareTo(other.isSetStartTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startTimestamp, other.startTimestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEndTimestamp()).compareTo(other.isSetEndTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEndTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.endTimestamp, other.endTimestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSets()).compareTo(other.isSetSets());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSets()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sets, other.sets);
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
    StringBuilder sb = new StringBuilder("WorkoutSession(");
    boolean first = true;

    sb.append("startTimestamp:");
    sb.append(this.startTimestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("endTimestamp:");
    sb.append(this.endTimestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("sets:");
    if (this.sets == null) {
      sb.append("null");
    } else {
      sb.append(this.sets);
    }
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

  private static class WorkoutSessionStandardSchemeFactory implements SchemeFactory {
    public WorkoutSessionStandardScheme getScheme() {
      return new WorkoutSessionStandardScheme();
    }
  }

  private static class WorkoutSessionStandardScheme extends StandardScheme<WorkoutSession> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WorkoutSession struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // START_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.startTimestamp = iprot.readI64();
              struct.setStartTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // END_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.endTimestamp = iprot.readI64();
              struct.setEndTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SETS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.sets = new ArrayList<ExerciseSet>(_list0.size);
                ExerciseSet _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new ExerciseSet();
                  _elem1.read(iprot);
                  struct.sets.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setSetsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, WorkoutSession struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(START_TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.startTimestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(END_TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.endTimestamp);
      oprot.writeFieldEnd();
      if (struct.sets != null) {
        oprot.writeFieldBegin(SETS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.sets.size()));
          for (ExerciseSet _iter3 : struct.sets)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WorkoutSessionTupleSchemeFactory implements SchemeFactory {
    public WorkoutSessionTupleScheme getScheme() {
      return new WorkoutSessionTupleScheme();
    }
  }

  private static class WorkoutSessionTupleScheme extends TupleScheme<WorkoutSession> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WorkoutSession struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetStartTimestamp()) {
        optionals.set(0);
      }
      if (struct.isSetEndTimestamp()) {
        optionals.set(1);
      }
      if (struct.isSetSets()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetStartTimestamp()) {
        oprot.writeI64(struct.startTimestamp);
      }
      if (struct.isSetEndTimestamp()) {
        oprot.writeI64(struct.endTimestamp);
      }
      if (struct.isSetSets()) {
        {
          oprot.writeI32(struct.sets.size());
          for (ExerciseSet _iter4 : struct.sets)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WorkoutSession struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.startTimestamp = iprot.readI64();
        struct.setStartTimestampIsSet(true);
      }
      if (incoming.get(1)) {
        struct.endTimestamp = iprot.readI64();
        struct.setEndTimestampIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.sets = new ArrayList<ExerciseSet>(_list5.size);
          ExerciseSet _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new ExerciseSet();
            _elem6.read(iprot);
            struct.sets.add(_elem6);
          }
        }
        struct.setSetsIsSet(true);
      }
    }
  }

}
