require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"
require "TP1/Constructors/Constructors"

module TP

  class PrototypedConstructor

    attr_accessor :prototype, :builder, :extends

    def self.copy(prototype)
      new_proto_constructor = PrototypedConstructor.new(prototype)
      new_proto_constructor.builder = CopyConstructor.new
      new_proto_constructor
    end

    def initialize(prototype, block = nil)

      self.prototype = prototype

      if block.nil? then
        self.builder = HashConstructor.new
      else
        self.builder = ProcConstructor.new(block)
      end

    end

    def new(*args)

      new_object = Object.new
      new_object.extend Prototyped
      new_object.set_prototype(self.prototype)

      new_object = self.builder.build(new_object, *args)

      new_object
    end


  end




end