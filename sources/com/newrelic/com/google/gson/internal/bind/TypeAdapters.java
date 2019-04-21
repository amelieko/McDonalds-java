package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonIOException;
import com.newrelic.com.google.gson.JsonNull;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.newrelic.com.google.gson.JsonSyntaxException;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.TypeAdapterFactory;
import com.newrelic.com.google.gson.annotations.SerializedName;
import com.newrelic.com.google.gson.internal.LazilyParsedNumber;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;

public final class TypeAdapters {
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new C423314();
    public static final TypeAdapter<BigInteger> BIG_INTEGER = new C423415();
    public static final TypeAdapter<BitSet> BIT_SET = new C42512();
    public static final TypeAdapterFactory BIT_SET_FACTORY = newFactory(BitSet.class, BIT_SET);
    public static final TypeAdapter<Boolean> BOOLEAN = new C42553();
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING = new C42564();
    public static final TypeAdapterFactory BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, BOOLEAN);
    public static final TypeAdapter<Number> BYTE = new C42575();
    public static final TypeAdapterFactory BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, BYTE);
    public static final TypeAdapter<Calendar> CALENDAR = new C424423();
    public static final TypeAdapterFactory CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, CALENDAR);
    public static final TypeAdapter<Character> CHARACTER = new C423112();
    public static final TypeAdapterFactory CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, CHARACTER);
    public static final TypeAdapter<Class> CLASS = new C42391();
    public static final TypeAdapterFactory CLASS_FACTORY = newFactory(Class.class, CLASS);
    public static final TypeAdapter<Number> DOUBLE = new C422910();
    public static final TypeAdapterFactory ENUM_FACTORY = newEnumTypeHierarchyFactory();
    public static final TypeAdapter<Number> FLOAT = new C42619();
    public static final TypeAdapter<InetAddress> INET_ADDRESS = new C424020();
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, INET_ADDRESS);
    public static final TypeAdapter<Number> INTEGER = new C42597();
    public static final TypeAdapterFactory INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, INTEGER);
    public static final TypeAdapter<JsonElement> JSON_ELEMENT = new C424625();
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, JSON_ELEMENT);
    public static final TypeAdapter<Locale> LOCALE = new C424524();
    public static final TypeAdapterFactory LOCALE_FACTORY = newFactory(Locale.class, LOCALE);
    public static final TypeAdapter<Number> LONG = new C42608();
    public static final TypeAdapter<Number> NUMBER = new C423011();
    public static final TypeAdapterFactory NUMBER_FACTORY = newFactory(Number.class, NUMBER);
    public static final TypeAdapter<Number> SHORT = new C42586();
    public static final TypeAdapterFactory SHORT_FACTORY = newFactory(Short.TYPE, Short.class, SHORT);
    public static final TypeAdapter<String> STRING = new C423213();
    public static final TypeAdapter<StringBuffer> STRING_BUFFER = new C423617();
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, STRING_BUFFER);
    public static final TypeAdapter<StringBuilder> STRING_BUILDER = new C423516();
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, STRING_BUILDER);
    public static final TypeAdapterFactory STRING_FACTORY = newFactory(String.class, STRING);
    public static final TypeAdapterFactory TIMESTAMP_FACTORY = new C424322();
    public static final TypeAdapter<URI> URI = new C423819();
    public static final TypeAdapterFactory URI_FACTORY = newFactory(URI.class, URI);
    public static final TypeAdapter<URL> URL = new C423718();
    public static final TypeAdapterFactory URL_FACTORY = newFactory(URL.class, URL);
    public static final TypeAdapter<UUID> UUID = new C424121();
    public static final TypeAdapterFactory UUID_FACTORY = newFactory(UUID.class, UUID);

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$10 */
    static class C422910 extends TypeAdapter<Number> {
        C422910() {
        }

        public Number read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Double.valueOf(in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$11 */
    static class C423011 extends TypeAdapter<Number> {
        C423011() {
        }

        public Number read(JsonReader in) throws IOException {
            JsonToken jsonToken = in.peek();
            switch (C425432.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken.ordinal()]) {
                case 1:
                    return new LazilyParsedNumber(in.nextString());
                case 4:
                    in.nextNull();
                    return null;
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
            }
        }

        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$12 */
    static class C423112 extends TypeAdapter<Character> {
        C423112() {
        }

        public Character read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String str = in.nextString();
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + str);
        }

        public void write(JsonWriter out, Character value) throws IOException {
            out.value(value == null ? null : String.valueOf(value));
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$13 */
    static class C423213 extends TypeAdapter<String> {
        C423213() {
        }

        public String read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            if (peek == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else if (peek == JsonToken.BOOLEAN) {
                return Boolean.toString(in.nextBoolean());
            } else {
                return in.nextString();
            }
        }

        public void write(JsonWriter out, String value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$14 */
    static class C423314 extends TypeAdapter<BigDecimal> {
        C423314() {
        }

        public BigDecimal read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return new BigDecimal(in.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, BigDecimal value) throws IOException {
            out.value((Number) value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$15 */
    static class C423415 extends TypeAdapter<BigInteger> {
        C423415() {
        }

        public BigInteger read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return new BigInteger(in.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, BigInteger value) throws IOException {
            out.value((Number) value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$16 */
    static class C423516 extends TypeAdapter<StringBuilder> {
        C423516() {
        }

        public StringBuilder read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return new StringBuilder(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, StringBuilder value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$17 */
    static class C423617 extends TypeAdapter<StringBuffer> {
        C423617() {
        }

        public StringBuffer read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return new StringBuffer(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, StringBuffer value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$18 */
    static class C423718 extends TypeAdapter<URL> {
        C423718() {
        }

        public URL read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String nextString = in.nextString();
            if (SafeJsonPrimitive.NULL_STRING.equals(nextString)) {
                return null;
            }
            return new URL(nextString);
        }

        public void write(JsonWriter out, URL value) throws IOException {
            out.value(value == null ? null : value.toExternalForm());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$19 */
    static class C423819 extends TypeAdapter<URI> {
        C423819() {
        }

        public URI read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String nextString = in.nextString();
                if (SafeJsonPrimitive.NULL_STRING.equals(nextString)) {
                    return null;
                }
                return new URI(nextString);
            } catch (URISyntaxException e) {
                throw new JsonIOException(e);
            }
        }

        public void write(JsonWriter out, URI value) throws IOException {
            out.value(value == null ? null : value.toASCIIString());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$1 */
    static class C42391 extends TypeAdapter<Class> {
        C42391() {
        }

        public void write(JsonWriter out, Class value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + value.getName() + ". Forgot to register a type adapter?");
        }

        public Class read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$20 */
    static class C424020 extends TypeAdapter<InetAddress> {
        C424020() {
        }

        public InetAddress read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return InetAddress.getByName(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, InetAddress value) throws IOException {
            out.value(value == null ? null : value.getHostAddress());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$21 */
    static class C424121 extends TypeAdapter<UUID> {
        C424121() {
        }

        public UUID read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return UUID.fromString(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, UUID value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$22 */
    static class C424322 implements TypeAdapterFactory {
        C424322() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() != Timestamp.class) {
                return null;
            }
            final TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
            return new TypeAdapter<Timestamp>() {
                public Timestamp read(JsonReader in) throws IOException {
                    Date date = (Date) dateTypeAdapter.read(in);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void write(JsonWriter out, Timestamp value) throws IOException {
                    dateTypeAdapter.write(out, value);
                }
            };
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$23 */
    static class C424423 extends TypeAdapter<Calendar> {
        private static final String DAY_OF_MONTH = "dayOfMonth";
        private static final String HOUR_OF_DAY = "hourOfDay";
        private static final String MINUTE = "minute";
        private static final String MONTH = "month";
        private static final String SECOND = "second";
        private static final String YEAR = "year";

        C424423() {
        }

        public Calendar read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            in.beginObject();
            int year = 0;
            int month = 0;
            int dayOfMonth = 0;
            int hourOfDay = 0;
            int minute = 0;
            int second = 0;
            while (in.peek() != JsonToken.END_OBJECT) {
                String name = in.nextName();
                int value = in.nextInt();
                if (YEAR.equals(name)) {
                    year = value;
                } else if (MONTH.equals(name)) {
                    month = value;
                } else if (DAY_OF_MONTH.equals(name)) {
                    dayOfMonth = value;
                } else if (HOUR_OF_DAY.equals(name)) {
                    hourOfDay = value;
                } else if (MINUTE.equals(name)) {
                    minute = value;
                } else if (SECOND.equals(name)) {
                    second = value;
                }
            }
            in.endObject();
            return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
        }

        public void write(JsonWriter out, Calendar value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(YEAR);
            out.value((long) value.get(1));
            out.name(MONTH);
            out.value((long) value.get(2));
            out.name(DAY_OF_MONTH);
            out.value((long) value.get(5));
            out.name(HOUR_OF_DAY);
            out.value((long) value.get(11));
            out.name(MINUTE);
            out.value((long) value.get(12));
            out.name(SECOND);
            out.value((long) value.get(13));
            out.endObject();
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$24 */
    static class C424524 extends TypeAdapter<Locale> {
        C424524() {
        }

        public Locale read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            StringTokenizer tokenizer = new StringTokenizer(in.nextString(), "_");
            String language = null;
            String country = null;
            String variant = null;
            if (tokenizer.hasMoreElements()) {
                language = tokenizer.nextToken();
            }
            if (tokenizer.hasMoreElements()) {
                country = tokenizer.nextToken();
            }
            if (tokenizer.hasMoreElements()) {
                variant = tokenizer.nextToken();
            }
            if (country == null && variant == null) {
                return new Locale(language);
            }
            if (variant == null) {
                return new Locale(language, country);
            }
            return new Locale(language, country, variant);
        }

        public void write(JsonWriter out, Locale value) throws IOException {
            out.value(value == null ? null : value.toString());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$25 */
    static class C424625 extends TypeAdapter<JsonElement> {
        C424625() {
        }

        public JsonElement read(JsonReader in) throws IOException {
            switch (C425432.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
                case 1:
                    return new JsonPrimitive(new LazilyParsedNumber(in.nextString()));
                case 2:
                    return new JsonPrimitive(Boolean.valueOf(in.nextBoolean()));
                case 3:
                    return new JsonPrimitive(in.nextString());
                case 4:
                    in.nextNull();
                    return JsonNull.INSTANCE;
                case 5:
                    JsonElement array = new JsonArray();
                    in.beginArray();
                    while (in.hasNext()) {
                        array.add(read(in));
                    }
                    in.endArray();
                    return array;
                case 6:
                    JsonElement object = new JsonObject();
                    in.beginObject();
                    while (in.hasNext()) {
                        object.add(in.nextName(), read(in));
                    }
                    in.endObject();
                    return object;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void write(JsonWriter out, JsonElement value) throws IOException {
            Iterator i$;
            if (value == null || value.isJsonNull()) {
                out.nullValue();
            } else if (value.isJsonPrimitive()) {
                JsonPrimitive primitive = value.getAsJsonPrimitive();
                if (primitive.isNumber()) {
                    out.value(primitive.getAsNumber());
                } else if (primitive.isBoolean()) {
                    out.value(primitive.getAsBoolean());
                } else {
                    out.value(primitive.getAsString());
                }
            } else if (value.isJsonArray()) {
                out.beginArray();
                i$ = value.getAsJsonArray().iterator();
                while (i$.hasNext()) {
                    write(out, (JsonElement) i$.next());
                }
                out.endArray();
            } else if (value.isJsonObject()) {
                out.beginObject();
                for (Entry<String, JsonElement> e : value.getAsJsonObject().entrySet()) {
                    out.name((String) e.getKey());
                    write(out, (JsonElement) e.getValue());
                }
                out.endObject();
            } else {
                throw new IllegalArgumentException("Couldn't write " + value.getClass());
            }
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$26 */
    static class C424726 implements TypeAdapterFactory {
        C424726() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new EnumTypeAdapter(rawType);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$2 */
    static class C42512 extends TypeAdapter<BitSet> {
        C42512() {
        }

        public BitSet read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            BitSet bitset = new BitSet();
            in.beginArray();
            int i = 0;
            JsonToken tokenType = in.peek();
            while (tokenType != JsonToken.END_ARRAY) {
                boolean set;
                switch (C425432.$SwitchMap$com$google$gson$stream$JsonToken[tokenType.ordinal()]) {
                    case 1:
                        if (in.nextInt() == 0) {
                            set = false;
                            break;
                        }
                        set = true;
                        break;
                    case 2:
                        set = in.nextBoolean();
                        break;
                    case 3:
                        String stringValue = in.nextString();
                        try {
                            if (Integer.parseInt(stringValue) == 0) {
                                set = false;
                                break;
                            }
                            set = true;
                            break;
                        } catch (NumberFormatException e) {
                            throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + stringValue);
                        }
                    default:
                        throw new JsonSyntaxException("Invalid bitset value type: " + tokenType);
                }
                if (set) {
                    bitset.set(i);
                }
                i++;
                tokenType = in.peek();
            }
            in.endArray();
            return bitset;
        }

        public void write(JsonWriter out, BitSet src) throws IOException {
            if (src == null) {
                out.nullValue();
                return;
            }
            out.beginArray();
            for (int i = 0; i < src.length(); i++) {
                out.value((long) (src.get(i) ? 1 : 0));
            }
            out.endArray();
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$32 */
    static /* synthetic */ class C425432 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$3 */
    static class C42553 extends TypeAdapter<Boolean> {
        C42553() {
        }

        public Boolean read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else if (in.peek() == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(in.nextString()));
            } else {
                return Boolean.valueOf(in.nextBoolean());
            }
        }

        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.booleanValue());
            }
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$4 */
    static class C42564 extends TypeAdapter<Boolean> {
        C42564() {
        }

        public Boolean read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Boolean.valueOf(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Boolean value) throws IOException {
            out.value(value == null ? SafeJsonPrimitive.NULL_STRING : value.toString());
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$5 */
    static class C42575 extends TypeAdapter<Number> {
        C42575() {
        }

        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) in.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$6 */
    static class C42586 extends TypeAdapter<Number> {
        C42586() {
        }

        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) in.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$7 */
    static class C42597 extends TypeAdapter<Number> {
        C42597() {
        }

        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(in.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$8 */
    static class C42608 extends TypeAdapter<Number> {
        C42608() {
        }

        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Long.valueOf(in.nextLong());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.TypeAdapters$9 */
    static class C42619 extends TypeAdapter<Number> {
        C42619() {
        }

        public Number read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return Float.valueOf((float) in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    }

    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<T, String> constantToName = new HashMap();
        private final Map<String, T> nameToConstant = new HashMap();

        public EnumTypeAdapter(Class<T> classOfT) {
            try {
                for (T constant : (Enum[]) classOfT.getEnumConstants()) {
                    String name = constant.name();
                    SerializedName annotation = (SerializedName) classOfT.getField(name).getAnnotation(SerializedName.class);
                    if (annotation != null) {
                        name = annotation.value();
                    }
                    this.nameToConstant.put(name, constant);
                    this.constantToName.put(constant, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public T read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return (Enum) this.nameToConstant.get(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, T value) throws IOException {
            out.value(value == null ? null : (String) this.constantToName.get(value));
        }
    }

    private TypeAdapters() {
    }

    public static TypeAdapterFactory newEnumTypeHierarchyFactory() {
        return new C424726();
    }

    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> type, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.equals(type) ? typeAdapter : null;
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> type, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.getRawType() == type ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[type=" + type.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> unboxed, final Class<TT> boxed, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == unboxed || rawType == boxed) ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[type=" + boxed.getName() + "+" + unboxed.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> base, final Class<? extends TT> sub, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == base || rawType == sub) ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[type=" + base.getName() + "+" + sub.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newTypeHierarchyFactory(final Class<TT> clazz, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return clazz.isAssignableFrom(typeToken.getRawType()) ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + clazz.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
